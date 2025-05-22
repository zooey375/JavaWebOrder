package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.OrderDTO;
import service.OrderService;

@WebServlet("/order/update")
public class OrderUpdateServlet extends HttpServlet {
	
	private OrderService orderService = new OrderService();
	
	// 根據 index 呈現修改表單(update.jsp)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String index = req.getParameter("index");
		// 重導到指定 jsp 並帶上 index
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/update.jsp");
		req.setAttribute("index", index);
		rd.forward(req, resp);
	}

	// 修改表單
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String index = req.getParameter("index");
		String item = req.getParameter("item");
		OrderDTO orderDTO = orderService.updateOrder(Integer.parseInt(index), item);
		// 重導到指定 jsp 並帶上歷史反饋資料(OrderDTO)
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
	}
}
