import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App {
    public static void main(String[] args) throws Exception {


        // Fazer uma conexão HTTP e buscar os top 250 filmes.
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request , BodyHandlers.ofString());
        String body = response.body();
        

        
        // Pegar só os dados que interessam (Titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>>listaDeFilmes = parser.parse(body);
        

        var diretorio = new File("figurinhas/");
        diretorio.mkdir();


        // Exibir e manipular os dados.
        var geradora =  new GeradoraFigurinha();
        for (int i = 0; i < 3; i++){
        Map<String,String> filme = listaDeFilmes.get(i);


        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        
        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = "figurinhas/" +  titulo + ".png"; 

           
           geradora.cria(inputStream, nomeArquivo);
            
            System.out.println("\u001B[1m Titulo: \u001B[m " + filme.get("title"));
            System.out.println("\u001b[1m Clasificação: \u001b[m " + filme.get("imDbRating"));
            System.out.println();

            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroDeEstrelinhas = (int)classificacao;
            


            for (int n = 1; n <= numeroDeEstrelinhas; n++) {
                System.out.print("\u001b[37;1m \u001b[44;1m ⭐\u001b[m");
            }
            System.out.println("\n");
        }


    }
}
