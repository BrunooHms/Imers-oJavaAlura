import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        API api = API.GITHUB_MOST_POPULAR_TVS;
        String url = api.getUrl();

        ExtratorDeConteudo extrator = api.getExtrator();
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoGitHub();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        
        var geradora = new GeradoraFigurinha();
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001B[1m Titulo: \u001B[m " + conteudo.titulo());
            System.out.println();

        }

    }

}
