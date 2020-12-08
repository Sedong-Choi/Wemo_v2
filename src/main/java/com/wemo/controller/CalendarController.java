package com.wemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wemo.dao.CalendarDAO;
import com.wemo.domain.Calendarbean;

@Controller
public class CalendarController {

	@Autowired
	CalendarDAO calendardao;

	@RequestMapping("/Calendar")
	public String newCalendar(HttpServletRequest req, HttpSession session) {
		String USER_EMAIL = req.getParameter("USER_EMAIL");
		session.setAttribute("USER_EMAIL", USER_EMAIL);
		return "Calendar";
	}

	@ResponseBody
	@GetMapping("/CalendarWrite.net")
	public void calendar_write(Calendarbean calendarbean, HttpServletResponse resp) throws Exception {

		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		System.out.println(calendarbean.getStart());
		try {
			out = resp.getWriter();
			System.out.println(calendarbean.isAllDay());
			if(calendarbean.isAllDay()) {
				calendarbean.setCALENDAR_ALLDAY("1");
			} else {
				calendarbean.setCALENDAR_ALLDAY("0");
			}

			if(calendardao.insert(calendarbean) > 0) {
				out.println("success");
			} else {
				out.println("<script>alert('insert failed')</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	@GetMapping("/calendarDeleteAction.net")
	public void calendarDeleteAction(Calendarbean calendarbean, HttpServletResponse resp, HttpServletRequest req) throws Exception {

		resp.setCharacterEncoding("utf-8");
		PrintWriter out = null;

		try {

			out = resp.getWriter();

			if (calendardao.delete(calendarbean) > 0) {
				out.println("delete calendar event success");
			} else {
				out.println("delete calendar event failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

	}

	@ResponseBody
	@RequestMapping(value = { "/calendarupdate.net",
			"/calendarREupdate.net",
			"/calendarDGupdate.net" },
			method = RequestMethod.GET)
	public void calendarREupdate(Calendarbean calendarbean, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;

		try {

			out = response.getWriter();

			if (calendardao.update(calendarbean) > 0) {
				out.print("calendar update success");
			} else {
				out.println("<script>alert('calendar update failed')</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null)
				out.close();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/calendarListAjax.net", produces = "application/text;charset=utf-8", method = RequestMethod.POST)
	public void calendarListAjax(Calendarbean CalObj, HttpServletResponse resp) {

		resp.setCharacterEncoding("UTF-8");
		List<Calendarbean> list =null;
		PrintWriter out = null;

		try {

			out = resp.getWriter();
			list = calendardao.getcalendarList(CalObj);

			for(Calendarbean calbean : list) {
				if(calbean.getCALENDAR_ALLDAY().equals("Y"))
					calbean.setAllDay(true);
				else
					calbean.setAllDay(false);
			}

			if (list != null) {
				String jsonListObj = new Gson().toJson(list);
				out.println(jsonListObj);
				System.out.println("JsonListObj:" + jsonListObj);
			} else {
				out.print("<script></script>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

	}
}