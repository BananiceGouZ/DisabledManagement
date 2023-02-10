//package cn.bananice;
//
//
//import cn.bananice.pet.domain.Pet;
//import cn.bananice.pet.domain.PetDetail;
//import cn.bananice.pet.mapper.PetDetailMapper;
//import cn.bananice.pet.mapper.PetMapper;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.math.BigDecimal;
//import java.util.Random;
//
//public class PetTest extends BaseTest {
//
//    @Autowired
//    private PetMapper petMapper;
//
//    @Autowired
//    private PetDetailMapper petDetailMapper;
//
//    @Test
//    public void testAdd() throws Exception{
//
//        for (int i = 0; i < 32; i++) {
//            Pet pet = petMapper.loadById(58L);
//            PetDetail petDetail = petDetailMapper.loadById(58L);
//
//            pet.setId(null);
//            petDetail.setId(null);
//
//            pet.setName(pet.getName() + i);
//
//            Random random = new Random();
//            int randomData = random.nextInt(10);
//
//            pet.setCostprice(pet.getCostprice().multiply(new BigDecimal(randomData)));
//            pet.setSaleprice(pet.getCostprice().divide(new BigDecimal(Math.random()),BigDecimal.ROUND_HALF_UP));
//
//            petDetail.setIntro(petDetail.getIntro() + i);
//
//            petMapper.save(pet);
//            petDetail.setPetId(pet.getId());
//            petDetailMapper.save(petDetail);
//        }
//
//
//
//    }
//}
