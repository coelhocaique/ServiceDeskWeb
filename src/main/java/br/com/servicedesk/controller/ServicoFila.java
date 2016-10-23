package br.com.servicedesk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.servicedesk.dao.FilaDao;
import br.com.servicedesk.ws.JSonFacadeChamado;
import br.com.servicedesk.ws.JSonFacadeFila;


/**
 * Servlet implementation class ServicoChamado
 */
@WebServlet("/ServicoFila.do")
public class ServicoFila extends HttpServlet {
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
	 * listar Tipos de Fila
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			FilaDao dao = new FilaDao();
			ArrayList<String> list = dao.listAll();
			out.println(JSonFacadeFila.listToJSon(list));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacadeChamado.errorToJSon(e));
		}
	}


}
