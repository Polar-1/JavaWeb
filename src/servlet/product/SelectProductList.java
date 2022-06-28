package servlet.product;

import entity.Category;
import entity.Product;
import entity.User;
import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.impl.CartServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectProductList")
public class SelectProductList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询购物车数量
        HttpSession session = request.getSession();
        //获取用户姓名
        User user = (User) session.getAttribute("name");
        //初始时，购物车为空
        String cartCount = "0";
        //实例化service层中CartService对象
        CartService service1 = new CartServiceImpl();
        if (user != null) {
            //调用service层中的findCartCountByUserId方法获取指定userid的购物车数量
            cartCount = String.valueOf(service1.findCartCountByUserId(user.getUser_id()));
        }else {
            cartCount = "?";
        }
        //存值
        request.setAttribute("cartCount",cartCount);

        //获取子类id参数
        String cid = request.getParameter("cid");
        if (cid != null && !cid.trim().equals("")) {
            //有cid
            //实例化service层中ProductService对象
            ProductService service = new ProductServiceImpl();
            //调用service层中findProductByCategoryCid方法
            List<Product> list = service.findProductByCategoryCid(Integer.valueOf(cid));
            //存入productList属性并赋值list
            request.setAttribute("productList",list);

            //实例化service层中CategoryService对象
            CategoryService service2 = new CategoryServiceImpl();
            //调用service层中findCategoryListByCid方法
            Category c = service2.findCategoryByCid(Integer.valueOf(cid));
            System.out.println(c.getCategory_name());
            request.setAttribute("childC",c);
            //*调用service层中findCategoryListByCid方法获取父类id
            Category f = service2.findCategoryByCid(c.getCategory_parentid());
            System.out.println(f.getCategory_name());
            request.setAttribute("fatherC",f);

            //调用service层中findCategoryListByName方法
            List<Category> flist =  service2.findCategoryListByName("father");

            request.setAttribute("flist", flist);
            //调用service层中findCategoryListByName方法
            List<Category> clist =  service2.findCategoryListByName("child");

            request.setAttribute("clist", clist);

            //*实现分类添加图片
            if (f.getCategory_id() == 1) {
                request.setAttribute("link", "images/temp/banner1.jpg");
            }else if (f.getCategory_id() == 2){
                request.setAttribute("link", "images/temp/bzbig.jpg");
            }else if (f.getCategory_id() == 3){
                request.setAttribute("link", "images/temp/banner2.jpg");
            }else if (f.getCategory_id() == 4){
                request.setAttribute("link", "images/temp/perfume_Banner.jpg");
            }else if (f.getCategory_id() == 5){
                request.setAttribute("link", "img/idea1.jpg");
            }else {
                request.setAttribute("link", "img/banner1.jpg");
            }
            request.getRequestDispatcher("productlist.jsp").forward(request, response);

        }else {

            //有fid
            //获取父类id参数
            String fid = request.getParameter("fid");
            //实例化service层中CategoryService对象
            CategoryService service2 = new CategoryServiceImpl();
            //调用service层中findCategoryListByCid方法获取父类id
            Category f = service2.findCategoryByCid(Integer.valueOf(fid));

            request.setAttribute("fatherC",f);
            //调用service层中findCategoryListByName方法
            List<Category> flist =  service2.findCategoryListByName("father");

            request.setAttribute("flist", flist);
            //调用service层中findCategoryListByName方法
            List<Category> clist =  service2.findCategoryListByName("child");

            request.setAttribute("clist", clist);

            //*实现分类添加图片
            if (f.getCategory_id() == 1) {
                request.setAttribute("link", "images/temp/banner1.jpg");
            }else if (f.getCategory_id() == 2){
                request.setAttribute("link", "images/temp/bzbig.jpg");
            }else if (f.getCategory_id() == 3){
                request.setAttribute("link", "images/temp/banner2.jpg");
            }else if (f.getCategory_id() == 4){
                request.setAttribute("link", "images/temp/perfume_Banner.jpg");
            }else if (f.getCategory_id() == 5){
                request.setAttribute("link", "img/idea1.jpg");
            }else {
                request.setAttribute("link", "img/banner1.jpg");
            }
            //实例化service层中CategoryService对象
            ProductService service = new ProductServiceImpl();
            //调用service层中findProductByCategoryFid方法
            List<Product> list = service.findProductByCategoryFid(Integer.valueOf(fid));
            //存入productList属性并赋值list
            request.setAttribute("productList",list);
            //请求重定向跳转为productlist.jsp
            request.getRequestDispatcher("productlist.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
