package eshop.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile/Logout")
public class Logout extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Logout() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    logout(request.getSession());
    response.sendRedirect("../index.jsp");
  }

  public static void logout(HttpSession session) {
    session.invalidate();
  }
}
