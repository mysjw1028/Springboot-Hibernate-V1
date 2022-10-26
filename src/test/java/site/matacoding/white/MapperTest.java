package site.matacoding.white;

import org.junit.jupiter.api.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//Builder는 키값대신에 넣어서 하는것  
//리플랙션은 접근제어자를 신경 안씀/ 나는 못하지만 스프링은 때릴수 잇다
// 스프링에서 DB -> rs -> Entity(전략 : 디폴트 생성자를 호출 한뒤 setter)
// 객체가 new 될때 초기화
// 스프링에서 DB -> rs -> Entity (전략 : 디폴트 생성자를 호출한 뒤 setter)
@Setter // 데이터를 변경 -> 히스토리 -> 계속해서 변화하는 것
@Getter // 값을 확인을할려면 필요하다
class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp; // 제조사

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, String mcp) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.mcp = mcp;
    }

}

@Getter
@Setter
class ProductDto {
    private String name;
    private Integer price;
    private Integer qty;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.qty = product.getQty();
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .qty(qty)
                .build();
    }

}

public class MapperTest {

    @Test
    public void 고급매핑하기() {
        // toEntity, toDto 만들어서 매핑하면 편하지 않을까? (재활용)
    }

    @Test
    public void 매핑하기1() {
        // 1. Product 객체생성 (디폴트)
        Product product = Product.builder()
                .id(1)
                .mcp("삼성")
                .name("바나나")
                .price(1000)
                .qty(100)
                .build();

        // 3. ProductDto 객체생성 (디폴트)
        ProductDto productDto = new ProductDto(product);

        // 4. 서비스에서 컨트롤러로 넘어가기 직전에
        // 5. ProductDto -> product 변경
        Product product2 = productDto.toEntity();
    }// 서비스에서 레파지토리로 넘어갈때
}