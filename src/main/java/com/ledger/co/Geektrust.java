package com.ledger.co;

import com.ledger.co.cmd.InputProviderImpl;
import com.ledger.co.domain.BalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class Geektrust implements ApplicationRunner {
    @Autowired
    private InputProviderImpl inputProvider;
    private static List<String> argsCmd;

    public static void main(String[] args) {
        argsCmd = Arrays.asList(args);
        SpringApplication.run(Geektrust.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (argsCmd != null) {
            String filePath = argsCmd.get(0);
            File file = new File(filePath);
            try {
                inputProvider.setupForCommand(file);
                List<BalanceResponse> balanceData = inputProvider.getBalanceData();
                balanceData.forEach(b -> {
                    System.out.println(b.getBankName() + " " + b.getBorrowerName() + " " + b.getAmountPaid() + " " + b.getEmiLeft());
                });
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("Enter input file path");
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Please enter valid path");
            } catch (IOException e) {
                System.out.println("Please Enter input");
            }
        }
    }
}
