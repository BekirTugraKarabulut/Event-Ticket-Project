package com.eventticket.service.impl;

import com.eventticket.dto.DtoCart;
import com.eventticket.dto.DtoCartCreate;
import com.eventticket.dto.DtoCartDetails;
import com.eventticket.dto.DtoTicket;
import com.eventticket.manager.TicketClient;
import com.eventticket.model.Cart;
import com.eventticket.repository.CartRepository;
import com.eventticket.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final TicketClient ticketClient;

    @Override
    public DtoCart createCart(DtoCartCreate dtoCartCreate) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if(dtoCartCreate.getQuantity() == null || dtoCartCreate.getQuantity() < 0){
            throw new RuntimeException("Bilet bulunamadı!");
        }

        DtoTicket ticket = ticketClient.getTicketById(dtoCartCreate.getTicketId());

        if(ticket == null){
            throw new RuntimeException("Bilet bulunamadı!");
        }

        Cart cart = Cart.builder()
                .username(username)
                .id(ticket.getId())
                .quantity(dtoCartCreate.getQuantity())
                .build();

        Cart dbCart = cartRepository.save(cart);

        DtoCart dtoCart = DtoCart.builder()
                .sepetId(dbCart.getSepetId())
                .username(dbCart.getUsername())
                .id(dbCart.getId())
                .quantity(dbCart.getQuantity())
                .build();

        return dtoCart;
    }

    @Override
    public List<DtoCartDetails> getCardListByUsername(String username) {

        List<Cart> carts = cartRepository.findByUsername(username);
        List<DtoCartDetails> dtoCartDetails = new ArrayList<>();

        for (Cart cart : carts) {
            DtoTicket dtoTicket = ticketClient.getTicketById(cart.getId());
            DtoCartDetails detail = DtoCartDetails.builder()
                            .sepetId(cart.getSepetId())
                            .id(cart.getId())
                            .name(dtoTicket.getName())
                            .quantity(cart.getQuantity())
                            .date(dtoTicket.getDate())
                            .day(dtoTicket.getDay())
                            .place(dtoTicket.getPlace())
                            .time(dtoTicket.getTime())
                            .price(dtoTicket.getPrice())
                            .imageUrl(dtoTicket.getImageUrl())
                            .accessCode(dtoTicket.getAccessCode())
                            .build();
            dtoCartDetails.add(detail);
        }

        return dtoCartDetails;
    }

    @Override
    public DtoCart updateCart(Long id, Integer quantity) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        Cart cart = cartRepository.findByUsernameAndSepetId(username, id)
                .orElseThrow(() -> new RuntimeException("Sepet ürünü bulunamadı!"));

        cart.setQuantity(quantity);

        cartRepository.save(cart);

        return DtoCart.builder()
                .sepetId(cart.getSepetId())
                .username(cart.getUsername())
                .id(cart.getId())
                .quantity(cart.getQuantity())
                .build();
    }

    @Override
    public void deleteCart(Long sepetId) {

        Optional<Cart> cart = cartRepository.findBySepetId(sepetId);

        if (cart.isPresent()) {
            cartRepository.delete(cart.get());
        }else{
            throw new RuntimeException("Sepet ürünü bulunamadı!");
        }

    }

    @Override
    public Integer sumPay(String username) {

        List<Cart> carts = cartRepository.findByUsername(username);

        return carts.stream()
                .map(cart -> {
                    DtoTicket dtoTicket = ticketClient.getTicketById(cart.getId());
                    return dtoTicket.getPrice().multiply(new BigDecimal(cart.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .intValue();
    }

    @Override
    public void deleteAllCartsByUsername(String username) {

        List<Cart> carts = cartRepository.findByUsername(username);

        if(carts.isEmpty()){
            throw new RuntimeException("Sepet ürünü bulunamadı!");
        }

        cartRepository.deleteAll(carts);

    }

}
