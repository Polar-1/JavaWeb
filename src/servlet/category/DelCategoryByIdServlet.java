package servlet.category;

import service.CategoryService;
import service.UserService;
import service.impl.CategoryServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delCategoryByIdServlet")
public class DelCategoryByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取分类id
        String cid = request.getParameter("id");
        int cid2 = Integer.valueOf(cid);
        //创建商品分类服务层对象 查询该分类后传到前台
        //实例化service层中CategoryService对象
        CategoryService service = new CategoryServiceImpl();
        //调用addCategory方法
        service.delCategoryById(cid2);

        request.getRequestDispatcher("/categoryListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
