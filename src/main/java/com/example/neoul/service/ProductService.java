package com.example.neoul.service;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import com.example.neoul.entity.user.User;
import com.example.neoul.entity.user.UserLikedProduct;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.ProductImageRepository;
import com.example.neoul.repository.ProductRepository;
import com.example.neoul.repository.UserLikedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserLikedProductRepository userLikedProductRepository;

    private final UserService userService;



    // 상품 전체 리스트
    public List<ProductRes.ProductDetailRes> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductRes.ProductDetailRes> result = new ArrayList<>();

        for(Product product : products){
            ProductRes.ProductDetailRes e = getProduct(product.getId());
            result.add(e);
        }

        return result;
    }

    public ProductRes.ProductDetailRes getProduct(Long productId) {
        Product product = getProductByProductId(productId);
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        List<String> productImgList = new ArrayList<>();

        for(ProductImage productImage : productImages){
            productImgList.add(productImage.getUrl());
        }

        return ProductRes.ProductDetailRes.builder()
                .productId(product.getId())
                .brandId(product.getBrand().getId())
                .categoryPId(product.getCategoryP().getId())
                .categoryName(product.getCategoryP().getName()) //얘는 살려야함
                .productName(product.getName())
                .price(product.getPrice())
                .productImgList(productImgList) //얘는 살려야함
                .deliveryInfo(product.getDeliveryInfo())
                .productUrl(product.getProductUrl())
//                .pLikeCNT(product.getLikeCount())
//                .pHearted(product.isHearted())
                .createdAt(product.getCreatedAt().toLocalDate())
//                .clickedAt(product.getClickedAt())
                .build();
    }


    //상품 상세조회
    public Product getProductByProductId(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()) {
            throw new NotFoundException("존재하지 않는 상품입니다");
        }
        return optionalProduct.get();
    }


    /*
    //상품 찜&찜취소
    @Transactional
    public ProductRes.makeLikedProductRes makeLikedProduct(Long productId) {
        User user = userService.findNowLoginUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("상품이 존재하지 않습니다."));

<<<<<<< HEAD
        //UserLikedProduct userLikedProduct = userLikedProductRepository.findByUserAndProduct(user, product);
        Optional<UserLikedProduct> userLikedProduct = userLikedProductRepository.findByUserAndProduct(user, product);

        if (userLikedProduct != null) {
            // 찜 했으면 엔티티에서 삭제
            userLikedProductRepository.delete(userLikedProduct);
            return new ProductRes.makeLikedProductRes(false); // 상품 찜 취소

            Product likedProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
            likedProduct.setLikedUser(null);
            productRepository.save(likedProduct);
            ProductRes.makeLikedProductRes likedProductRes= ProductRes.makeLikedProductRes.builder()
                    .productId(productId)
                    .liked(false)
                    .build();

            return likedProductRes;

        } else {
            // 찜 안했으면 엔티티에 생성
            UserLikedProduct newUserLikedProduct = UserLikedProduct.builder()
                    .user(user)
                    .product(product)
                    .build();
            userLikedProductRepository.save(newUserLikedProduct);
            return new ProductRes.makeLikedProductRes(true); // 상품 찜 등록

            Product likedProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
            likedProduct.setLikedUser(user);
            productRepository.save(likedProduct);
            ProductRes.makeLikedProductRes likedProductRes= ProductRes.makeLikedProductRes.builder()
                    .productId(productId)
                    .liked(true)
                    .build();

            return likedProductRes;
        }
    }
    */



    /*//찜한 상품 목록 조회
    public ProductRes.getLikedProductRes getLikedProduct() {
        User user = userService.findNowLoginUser();
        List<ProductRes.LikedProductList> likedProductList = userLikedProductRepository.findAllByLikedUser(user).stream()
                .map(origin -> {
                    ProductRes.LikedProductList likedProduct = ProductRes.LikedProductList.builder()
                            .likedProductId(origin.getId())
                            .brandId(origin.getBrand().getId())
                            .brandName(origin.getBrand().getName())
                            .productName(origin.getName())
                            .price(origin.getPrice())
                            .build();
                    return likedProduct;
                })
                .collect(Collectors.toList());

        return ProductRes.getLikedProductRes.builder()
                .count(likedProductList.size())
                .likedProducts(likedProductList)
=======
    }*/

    public void likeProduct(Long productId) {
        User user = userService.findNowLoginUser();
        Product product = getProductByProductId(productId);
        UserLikedProduct userLikedProduct = UserLikedProduct.builder()
                .user(user)
                .product(product)
                .build();

        userLikedProductRepository.save(userLikedProduct);
    }

    public void deleteLikedProduct(Long productId) {
        User user = userService.findNowLoginUser();
        Product product = getProductByProductId(productId);

        UserLikedProduct userLikedProduct = userLikedProductRepository.findByUserAndProduct(user, product).orElse(null);

        if(userLikedProduct == null)
            throw new BadRequestException("해당 상품을 찜하지 않았습니다");

        userLikedProductRepository.delete(userLikedProduct);
    }

    public ProductRes.getLikedProductRes getUserLikedProduct() {
        User user = userService.findNowLoginUser();
        List<UserLikedProduct> userLikedProductList = userLikedProductRepository.findAllByUser(user);
        List<ProductRes.LikedProductList> list = new ArrayList<>();

        for(UserLikedProduct userLikedProduct : userLikedProductList){
            ProductRes.LikedProductList e = ProductRes.LikedProductList.builder() //likedProductId
                    .productId(userLikedProduct.getProduct().getId())
                    .productName(userLikedProduct.getProduct().getName())
                    .build();
            list.add(e);
        }


        return ProductRes.getLikedProductRes.builder()
                .userId(user.getUserId())
                .productCnt(list.size())
                .likedProduct(list)
                .build();
    }





}
