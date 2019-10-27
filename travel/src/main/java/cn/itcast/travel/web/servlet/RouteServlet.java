package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数
        //当前页码
        String currentPageStr = request.getParameter("currentPage");
        //每页显示条数
        String pageSizeStr = request.getParameter("pageSize");
        //类别id
        String cidStr = request.getParameter("cid");
        //获取rname线路名称
        String rname = request.getParameter("rname");
        //解决乱码
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");


        //处理参数
        int cid = 0;//类别id
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;//每页显示的条数，如果不传递，则默认为每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //调用service查询PageBean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);

        //将PageBean对象序列化为json返回
        writeValue(pb, response);
    }


    /**
     * 根据id查询一个旅游线路的详情信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收id
        String rid = request.getParameter("rid");

        //调用service查询route对象
        Route route = service.findOne(rid);
        //转为json返回客户端
        writeValue(route, response);

    }

    /**
     * 判断当前登录用户是否收藏过该铁路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取线路的id
        String rid = request.getParameter("rid");
        //获取当前登录的用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            uid = 0;
        } else {
            //用户已登录
            uid = user.getUid();
        }

        //调用favoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //写回客户端
        writeValue(flag, response);


    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取线路rid
        String rid = request.getParameter("rid");

        //获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            return;
        } else {
            //用户已登录
            uid = user.getUid();
        }
        //调用Service添加
        favoriteService.add(rid,uid);
    }

}