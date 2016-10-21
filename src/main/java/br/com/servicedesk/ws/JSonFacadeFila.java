package br.com.servicedesk.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class JSonFacadeFila {

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

	public static String listToJSon(ArrayList<String> lista) {
		JSONArray vetor = new JSONArray();
		for (String filaNomes : lista) {
			vetor.put(filaNomes);
		}
		return vetor.toString();
	}

	public static ArrayList<String> jsonToList(String lista) {
		ArrayList<String> listRet = new ArrayList<>();
		try {
			
			JSONArray vetor = new JSONArray(lista);
			for (int i = 0; i < vetor.length(); i++) {
				listRet.add(vetor.getString(i));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listRet;
	}

}
