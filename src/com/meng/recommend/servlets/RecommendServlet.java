package com.meng.recommend.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meng.recommend.beans.DataResponse;
import com.meng.recommend.beans.DataResponse.Subtopic;
import com.meng.recommend.service.RecommendService;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/recommendations")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendServlet() {
        super();
    }

	/**
	 * recommendations?subtopic=X
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("subtopic");
		
		RecommendService service = RecommendService.getInstance();
		List<String> list = service.getRecommendation(topic, 4);
		
		DataResponse res = new DataResponse();
		
		if(list.size() == 0) {
			res.status = -1;
			res.msg = "the subtopic requested is not existed or no recommendations found!";
		} else {
			res.status = 1;
			res.msg = "request success!";
		}
		res.searchedTopic = topic;
		
		for (String string : list) {
			res.recommendations.add(new Subtopic(string));
		}
		Gson gson = new Gson();
		String json = gson.toJson(res);
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
