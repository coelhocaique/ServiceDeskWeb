package br.com.servicedesk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.servicedesk.model.Chamado;
import br.com.servicedesk.ws.JSonFacadeChamado;


/**
 * Servlet implementation class ServicoChamado
 */
@WebServlet("/ServicoChamado.do")
public class ServicoChamado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/*
	 * configurar a request e a response para todos os métodos
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}
	/*
	 * listar Chamados
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String chave = request.getParameter("chave");
		ArrayList<Chamado> lista = null;

		PrintWriter out = response.getWriter();

		try {
			if (chave != null && chave.length() > 0) {
				//lista = vendedor.listarChamados(chave);
			} else {
				//lista = vendedor.listarChamados();
			}
			out.println(JSonFacadeChamado.listToJSon(lista));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacadeChamado.errorToJSon(e));
		}
		
	}

	/*
	 * inclusão de Chamados
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacadeChamado.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Chamado chamado = JSonFacadeChamado.jSonToChamado(sb.toString());
			chamado = chamado.create();
			//retorna o Chamado cadastrado com o id atribuido pelo banco
			out.println(JSonFacadeChamado.chamadoToJSon(chamado));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacadeChamado.errorToJSon(e));
		}
	}
	/*
	 * atualiza Chamados
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacadeChamado.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Chamado chamado = JSonFacadeChamado.jSonToChamado(sb.toString());
			//chamado.atualizar();
			//retorna o Chamado atualizado
			out.println(JSonFacadeChamado.chamadoToJSon(chamado));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacadeChamado.errorToJSon(e));
		}
	}

	/*
	 * exclusão de Chamados
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = JSonFacadeChamado.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Chamado chamado = JSonFacadeChamado.jSonToChamado(sb.toString());
			//chamado.excluir(); 
			//retorna dados null se o Chamado foi deletado
			out.println(JSonFacadeChamado.chamadoToJSon(chamado));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacadeChamado.errorToJSon(e));
		}
	}

}
