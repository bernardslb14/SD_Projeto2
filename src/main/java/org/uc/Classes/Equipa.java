package org.uc.Classes;

import javax.persistence.*;
import java.util.List;

@Entity (name = "Equipa")
public class Equipa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome, imagem;

    //Um jogador só pertence a uma equipa e Uma equipa tem vários jogadores
    /*
        O mappedBy utiliza-se no lado não dominante da relação de modo a gerirmos os relacionamentos criados da melhor forma.
        A classe 'Equipa' é o lado dominante, pelo que temos de fazer uma referência na classe 'Jogo' usando o mappedBy e assim
        cria-se uma associação bidirecional ao invés de termos várias relações unidirecionais.

     */
    @OneToMany(mappedBy = "equipa")
    private List<Jogador> jogadoresEquipa;

    //Cada equipa vai ter uma lista de jogos associados
    /*
        Sem a classe 'Equipa', a classe 'Jogo' não tem qualquer significado. Logo utiliza-se o CascadeType para propagar
        as ações feitas nesta classe para aquela lhe está associada.
            Ex: Se eliminarmos uma equipa, os jogos que lhe estão associados também vão ser eliminados.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Jogo> jogos;

}
