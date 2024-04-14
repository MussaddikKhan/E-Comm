package com.clg.project.semVI.Ecommerce.Project.Service;

import com.clg.project.semVI.Ecommerce.Project.Exception.CartItemException;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.CartItem;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Repository.CartItemRepository;
import com.clg.project.semVI.Ecommerce.Project.Repository.CartRepository;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.CartItemService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementations implements CartItemService {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());

        cartItem.setDiscountedPrice((long) (cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity()));

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        User user  =   userService.findUserById(item.getUserId());
        //Item me jo User hai uski Id and Jo hamare pass User Hai uski Id dono same hai to hame update krna hai warna hame nhi krna (in simple word The Person who Created this Cart thus only change the CartItem )
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice((long) (item.getProduct().getDiscountedPrice()*item.getQuantity()));
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.findByCartAndProductAndSizeAndUserId(cart, product, size, userId);

        return cartItem;
    }

    @Override
    public void RemoveCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
            CartItem cartItem = findCartItemById(cartItemId);
            User user = userService.findUserById(cartItem.getUserId());
            User reqUser = userService.findUserById(userId);
            if(user.getId().equals(reqUser.getId())){
                cartItemRepository.deleteById(cartItemId);
            }
            else{
                throw  new UserException("You Can't remove another user items"); 
            }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt =  cartItemRepository.findById(cartItemId);
        if(opt.isPresent()) {
            return  opt.get();
        }
        else{
           throw new CartItemException("cart item not found with" + cartItemId); 
        }

    }
}
