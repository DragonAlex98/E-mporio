package com.emporio.emporio.unit.mappings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.emporio.emporio.dto.OrdineHistoryDto;
import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.model.Locker;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.model.StatoConsegna;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * OrdineMappingUnitTests
 */
public class OrdineMappingUnitTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertOrdineEntityToOrdineHistoryDto_thenCorrect() {
        Locker locker1 = Locker.builder().address("Via Alfreditica, 15").build();
        Posto posto1 = Posto.builder().nomePosto("A1").locker(locker1).build();

        Acquirente u1 = Acquirente.builder().username("aldo").build();
        Fattorino f1 = Fattorino.builder().username("giovanni").build();

        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();

        ProdottoDescrizione pdA = ProdottoDescrizione.builder().productCategory(cat1).productName("biscotti").build();
        ProdottoDescrizione pdB = ProdottoDescrizione.builder().productCategory(cat1).productName("latte").build();
        ProdottoDescrizione pdC = ProdottoDescrizione.builder().productCategory(cat2).productName("mannaia").build();

        Prodotto pA = Prodotto.builder().productDescription(pdA).build();
        Prodotto pB = Prodotto.builder().productDescription(pdB).build();
        Prodotto pC = Prodotto.builder().productDescription(pdC).build();

        CategoriaAttivita catA1 = CategoriaAttivita.builder().shopCategoryDescription("Falegnameria").build();
        

        //Creo catalogo
        Catalogo c1 = Catalogo.builder().build();
        c1.setProducts(Arrays.asList(pA, pB, pC).stream().collect(Collectors.toSet()));

        //Creo descrizione
        AttivitaDescrizione sd1 = AttivitaDescrizione.builder().shopPIVA("abc123").shopBusinessName("La Falegnameria").shopAddress("Via questa 1").shopHeadquarter("Milano").shopCategory(catA1).build();

        String park = "via brombeis";

        Ordine order2 = Ordine.builder()
                            .orderCustomer(u1)
                            .orderShop(sd1)
                            .parkingAddress(park)
                            .build();

        List<RigaOrdineProdotto> productsList = new ArrayList<>();

        productsList.add(RigaOrdineProdotto.builder().id(ChiaveRigaOrdineProdotto.builder()
                                    .orderId(order2.getOrderId())
                                    .productId(pdA.getProductId())
                                    .build())
                                    .order(order2)
                                    .product(pdA)
                                    .quantity(11)
                                    .build());

        productsList.add(RigaOrdineProdotto.builder().id(ChiaveRigaOrdineProdotto.builder()
                                    .orderId(order2.getOrderId())
                                    .productId(pdC.getProductId())
                                    .build())
                                    .order(order2)
                                    .product(pdC)
                                    .quantity(1)
                                    .build());

        order2.setOrderProductsLineList(productsList);

        Consegna consegna1 = Consegna.builder().ordine(order2).fattorino(f1)
                                                .statoConsegna(StatoConsegna.RITIRATA).posto(posto1).build();

        order2.setOrderConsegna(consegna1);

    /* Converter<Integer, Integer> convertlockerIdOnLockerTolockerIdOnDto = new Converter<Integer, Integer>()
    {
		@Override
		public Integer convert(MappingContext<Integer, Integer> context) {
			// TODO Auto-generated method stub
			return null;
		}
    }; */

        PropertyMap<Ordine, OrdineHistoryDto> mymap = new PropertyMap<Ordine, OrdineHistoryDto>()
        {
            protected void configure()
            {
                map(source.getOrderConsegna().getPosto().getLocker().getLockerId()).setLockerId(null);
                map(source.getOrderConsegna().getPosto().getLocker().getAddress()).setLockerAddress(null);
                map(source.getOrderConsegna().getPosto().getNomePosto()).setNomePosto(null);
            }
        };

        

        OrdineHistoryDto hist = this.modelMapper.addMappings(mymap).map(order2);

        assertEquals(order2.getParkingAddress(), hist.getParkingAddress());
        assertEquals(order2.getOrderShop().getShopPIVA() , hist.getOrderShop().getShopPIVA());
        assertEquals(order2.getOrderCustomer().getUsername(), hist.getOrderCustomerUsername());
        assertEquals(order2.getOrderConsegna().getStatoConsegna().toString(), hist.getStatoConsegna());
        assertEquals(order2.getOrderConsegna().getPosto().getLocker().getLockerId(), hist.getLockerId());
        assertEquals(order2.getOrderConsegna().getPosto().getLocker().getAddress(), hist.getLockerAddress());
        assertEquals(order2.getOrderConsegna().getPosto().getNomePosto(), hist.getNomePosto());
    }
}