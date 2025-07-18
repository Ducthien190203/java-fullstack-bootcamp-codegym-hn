package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.codegym.model.Product;
import vn.codegym.service.IProductService;
import vn.codegym.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        productService.save(product);
        redirect.addFlashAttribute("success", "Thêm sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Xóa sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model, RedirectAttributes redirect) {
        Product product = productService.findById(id);
        if (product == null) {
            redirect.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("products", productService.findByName(name));
        return "index";
    }
}