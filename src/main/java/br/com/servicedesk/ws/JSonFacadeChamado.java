package br.com.servicedesk.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.servicedesk.model.Chamado;

public class JSonFacadeChamado {

	public static StringBuilder montaJSon(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		return sb;
	}

	public static String listToJSon(ArrayList<Chamado> lista) {
		JSONArray vetor = new JSONArray();
		for (Chamado chamado : lista) {
			JSONObject object = new JSONObject();
			try {
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
				object.put("numero", chamado.getNumero());
				object.put("descricao", chamado.getDescricao());
				try {
					object.put("dataFechamento", dateformat.format(chamado.getDataDeFechamento()));
				} catch (Exception e) {
					object.put("dataFechamento", "");
				}
				try {
					object.put("dataAbertura", dateformat.format(chamado.getDataAbertura()));
				} catch (Exception e) {
					object.put("dataAbertura", "");
				}
				object.put("fila", chamado.getFila());
				object.put("status", chamado.getStatus());
				vetor.put(object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return vetor.toString();
	}

	public static Chamado jSonToChamado(String json) throws Exception {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
			JSONObject registro = new JSONObject(json);
			long numero = registro.getLong("numero");
			String descricao = registro.getString("descricao");
			Date abertura = null;
			try {
				abertura = dateformat.parse(registro.getString("dataAbertura"));
			} catch (Exception e) {
				e.printStackTrace();
				abertura = null;
			}

			Date fechamento;
			try {
				fechamento = dateformat.parse(registro.getString("dataFechamento"));
			} catch (Exception e) {
				fechamento = null;
			}
			int fila = registro.getInt("fila");
			int status = registro.getInt("status");

			return new Chamado(numero, descricao, fechamento, abertura, status, fila);
		} catch (JSONException jsone) {
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}

	public static String chamadoToJSon(Chamado chamado) throws IOException {
		JSONObject object = new JSONObject();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
			object.put("numero", chamado.getNumero());
			object.put("descricao", chamado.getDescricao());
			try {
				object.put("dataFechamento", dateformat.format(chamado.getDataDeFechamento()));
			} catch (Exception e) {
				object.put("dataFechamento", "");
			}
			try {
				object.put("dataAbertura", dateformat.format(chamado.getDataAbertura()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			object.put("fila", chamado.getFila());
			object.put("status", chamado.getStatus());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public static String errorToJSon(Exception e) {
		JSONObject object = new JSONObject();
		try {
			object.put("error", e.toString());
		} catch (JSONException e1) {
			e.printStackTrace();
		}
		return object.toString();
	}
}
