package com.silva.elto.logistics.clients;

import com.silva.elto.logistics.dto.ViaCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "viacep", url = "${url}")
public interface CepClient {

    @RequestMapping(method = RequestMethod.GET, value = "ws/{codigo}/json", produces = "application/json")
    public ViaCepDto getCep(@PathVariable("codigo") String codigo);
}
