package net.unibave.disciplinas.disciplina;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public DisciplinaDto listar() throws Exception {
        List<ServiceInstance> instances = discoveryClient.getInstances("alunos");
        ServiceInstance firstOne = instances.get(0);
        String uri = "http://" + firstOne.getHost() + ":" + firstOne.getPort();
        HttpGet getRequest = new HttpGet(uri + "/alunos");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getRequest);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
        Map mapContent = objectMapper.readValue(content, HashMap.class);
        List<Map> alunos = (List) PropertyUtils.getProperty(mapContent, "_embedded.alunos");
        List<String> nomes = alunos.stream()
                .map(m -> m.get("nome").toString())
                .collect(Collectors.toList());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        return DisciplinaDto.builder()
                .nome("Microservicos")
                .cargaHoraria(60)
                .dataInicio(dateFormat.parse("16/02/2018"))
                .alunosMatriculados(nomes)
                .build();

    }

}
