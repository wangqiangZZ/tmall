package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.Category;
//import com.joeqiang.tmall.service.CategoryService;
import com.joeqiang.tmall.service.CategoryService;
import com.joeqiang.tmall.util.ImageUtil;
import com.joeqiang.tmall.util.Paging;
import com.joeqiang.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/23.
 */
@Controller

public class PagingConterller {
    @Autowired
    CategoryService categoryService;

    /**
     * 分页查询
     *
     * @param model
     * @param paging
     * @return
     */
    @RequestMapping("paging")
    public String paging(Model model, Paging paging) {

        Integer count = paging.getCount();
        //当前页数
        Integer start = paging.getStart();
        if (start == 0) {
            start = 1;
        }
        System.out.println("当前页数" + start);
        //起始位置
        Integer startIndex = (start - 1) * count;
        //总数
        Integer total = categoryService.total();
        //分页数据
        List<Category> cs = categoryService.paging(startIndex, count);
        //总共分多少页
        Integer totalCount = (int) Math.ceil(1.0 * total / count);
        paging.setTotalCount(totalCount);
        paging.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", paging);

        return "admin/listCategory";
    }

    /**
     * 上传图片
     *
     * @param c
     * @param session
     * @param uploadedImageFile
     * @return
     * @throws IOException
     */
    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        //插入
        categoryService.add(c);
        //容器下的路径
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        //判断父目录是否存在
        if (!file.getParentFile().exists()) {
            //父目录调用mkdirs方法
            file.getParentFile().mkdirs();

        }
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
        //把浏览器传递过来的图片保存在上述指定的位置
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/paging";
    }

    /**
     * 删除图片
     */
    @RequestMapping("admin_category_delete")
    public String delete(Category c, HttpSession session) {
        categoryService.delete(c);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        file.delete();
        System.out.println("删除成功");

        return "redirect:/paging";
    }

    /**
     * 编辑
     */
    @RequestMapping("admin_category_edit")
    public String edit(Integer id, Model model) {
        Category c = categoryService.get(id);

        model.addAttribute("c", c);

        return "admin/editCategory";
    }

    /**
     * 修改
     */
    @RequestMapping("admin_category_update")
    public String update(Category category,HttpSession session,UploadedImageFile uploadedImageFile) throws IOException {

        String name = category.getName();
        Integer id = category.getId();
        System.out.println(name);
        System.out.println(id);
        //更新
        int update = categoryService.update(name, id);
        //活得图片
        MultipartFile image = uploadedImageFile.getImage();
        System.out.println(update);
        System.out.println("image"+image);
        //如果每页有图片,执行修改操作
        if (image!=null&&!image.isEmpty()){
            //获取父路径
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder, id + ".jpg");
            //将图片保存到指定位置
            image.transferTo(file);
            //转格式JPG
            BufferedImage img = ImageUtil.change2jpg(file);
            //发送给浏览器
            ImageIO.write(img, "jpg", file);
        }

        return "redirect:/paging";
    }
}