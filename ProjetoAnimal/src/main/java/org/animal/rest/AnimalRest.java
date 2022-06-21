package org.animal.rest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.animal.dao.AnimalDao;
import org.animal.info.Animal;


import com.google.gson.Gson;

/**
 * Servlet implementation class AnimalRest
 */
@WebServlet("/AnimalRest/*")
public class AnimalRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void enviaResposta(HttpServletResponse response, String json, int codigo) throws IOException {
		response.addHeader("Content-Type", "application/json; charset=UTF-8");
		response.addHeader("Acess-Control-Allow-Origin", "http://127.0.0.1:3000");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");

		response.setStatus(codigo);
		
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(json.getBytes("UTF-8"));
		out.close();
	}

    public AnimalRest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Animal> lista = new LinkedList();
		String json;
		int id = 0;
		if (request.getPathInfo() != null) {
			String info = request.getPathInfo().replace("/", "");
			id = Integer.parseInt(info);
		}
		Gson gson = new Gson();
		AnimalDao adao = new AnimalDao();
		
		if(id == 0) {
			lista = adao.listar();
			json = gson.toJson(lista);
		}else {
			Animal a = adao.consultar(id);
			json = gson.toJson(lista);
		}
		
		enviaResposta(response, json, 200);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// PEGANDO OS PARAMETROS ENVIADOS NO BODY DA REQUISIÇÃO
			String json = request.getReader().lines().collect(Collectors.joining());
			
			// PEGA A STRING JSON(PARAMETRO) ACIMA E A TRANSFORMA EM UM OBJETO
			Gson gson = new Gson();
			Animal a = (Animal) gson.fromJson(json, Animal.class);
			
			// INSERINDO UM NOVO ANIMAL
			AnimalDao adao = new AnimalDao();
			adao.inserir(a);
			
			// ENVIANDO RESPOSTA DE SUCESSO AO INSERIR ANIMAL.
			enviaResposta(response, "Animal inserido com sucesso!", 200);
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// PEGA OS PARAMETROS DO BODY ENVIADO NA REQUISIÇÃO
			String json = request.getReader().lines().collect(Collectors.joining());

			// TRANSFORMO NOVAMENTE A STRING JSON EM OBJETO, STRING QUE É O PARAMETRO PASSADO ACIMA.
			Gson gson = new Gson();
			Animal a = (Animal) gson.fromJson(json, Animal.class);

			// Altera time de futebol
			AnimalDao adao = new AnimalDao();
			adao.alterar(a);

			// Enviando resposta de sucesso
			enviaResposta(response, "Animal alterado com sucesso!", 200);

		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = 0;
			if (request.getPathInfo() != null) {
				String info = request.getPathInfo().replace("/", "");
				id = Integer.parseInt(info);
			}

			Animal a = new Animal();
			a.setIdanimal(id);
			// Deleta time de futebol
			AnimalDao adao = new AnimalDao();
			adao.excluir(a);

			// Enviando resposta de sucesso
			enviaResposta(response, "Excluido com sucesso", 200);

		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
	}

}
