package com.example.purebasketbe.domain.purchase;

import com.example.purebasketbe.domain.member.entity.Member;
import com.example.purebasketbe.domain.purchase.dto.PurchaseHistoryDto;
import com.example.purebasketbe.domain.purchase.dto.PurchaseRequestDto;
import com.example.purebasketbe.global.tool.LoginAccount;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Void> purchaseProducts(@RequestBody PurchaseRequestDto purchaseRequestDto,
                                                  @LoginAccount Member member) {
        purchaseService.purchaseProducts(purchaseRequestDto.getPurchaseList(), member);

        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("")).build();
    }

    @GetMapping("/histories")
    public ResponseEntity<Page<PurchaseHistoryDto>> getPurchaseHistory(
        @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue="purchasedAt") String sortBy,
        @RequestParam(defaultValue = "desc") String order, @LoginAccount Member member) {
        Page<PurchaseHistoryDto> responseBody = purchaseService.getPurchaseHistory(member, page - 1, sortBy, order);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
