import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoGitHub implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // Pegar só os dados que interessam (Titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image")))
                .toList();

    }

}
