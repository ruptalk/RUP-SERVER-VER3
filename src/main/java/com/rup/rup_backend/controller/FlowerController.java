package com.rup.rup_backend.controller;

import com.rup.rup_backend.dto.Flower;
import com.rup.rup_backend.dto.Success;
import com.rup.rup_backend.repository.FlowerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flower")
public class FlowerController {

    private final FlowerRepository flowerRepo;

    public FlowerController(FlowerRepository flowerRepo) {
        this.flowerRepo = flowerRepo;
    }

    @PostMapping("/add-new-flower")
    public Success addNewFlower(@RequestBody Flower requestFlower){
        String uid = requestFlower.getUid();
        String flower = requestFlower.getFlower();
        String flowerNickname = requestFlower.getFlowerNickname();

        Success returnSuccess = new Success();

        try{
            if(uid != null && flower != null){
                flowerRepo.insertFlower(uid, flower, flowerNickname);
                returnSuccess.setSuccess(true);
            }
            else{
                returnSuccess.setSuccess(false);
            }
        }
        catch(Exception e){
            returnSuccess.setSuccess(false);
        }

        return returnSuccess;
    }
}
