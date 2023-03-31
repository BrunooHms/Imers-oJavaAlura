import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;

public class GeradoraFigurinha {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // Leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Criar nova imagem em memória com tranasparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura , novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0 , 0 , null );

        //Configurar a fonte
        var fonte = new Font("Impact", Font.BOLD , 85);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // Escrever uma frase na nova imagem
        String texto = "MALADEZA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.006f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.blue);
        graphics.draw(outline);
        graphics.setClip(outline);


        // Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

   }

   

    
}
