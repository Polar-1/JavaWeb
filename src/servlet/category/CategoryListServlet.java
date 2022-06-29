package servlet.category;

import entity.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categoryListServlet")
public class CategoryListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建分类服务层对象 查询该分类后传到前台
        //实例化service层中CategoryService对象
        CategoryService service = new CategoryServiceImpl();
        //调用findAllCategory方法
        List<Category> catelist = service.findAllCategory();

        request.setAttribute("catelist", catelist);

        request.getRequestDispatcher("admin_cate.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
