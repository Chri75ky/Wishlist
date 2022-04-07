package com.example.wishlist.controllers;

import com.example.wishlist.Models.User;
import com.example.wishlist.Models.Wishlist;
import com.example.wishlist.repository.UserRepository;
import com.example.wishlist.repository.WishlistRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class WishlistController {
    WishlistRepo wr = new WishlistRepo();
    UserRepository ur = new UserRepository();

    @PostMapping("/wishlist")
    public String createWishlist(WebRequest dataFromForm, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current-user");

        int user_id = ur.getUserIdFromDB(currentUser.getEmail());

        Wishlist wishlistTest = new Wishlist(user_id, dataFromForm.getParameter("wishlist-name"), dataFromForm.getParameter("wishlist-description") );
        wr.insertWishlist(wishlistTest);
        return "redirect:/wishlist";
    }


}

