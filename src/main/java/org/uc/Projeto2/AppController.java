package org.uc.Projeto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.uc.Class.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;


@Controller
public class AppController {
    @Autowired
    UserService userService;

    @Autowired
    JogadorService jogadorService;

    @Autowired
    EquipaService equipaService;

    @Autowired
    JogoService jogoService;

    @Autowired
    GoloService goloService;

    @Autowired 
    OcorrenciaService ocorrenciaService;

    @Autowired
    CartaoService cartaoService;

    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String menu(Model m){
        m.addAttribute("user", new User());
        return "login";
    }



    @PostMapping("/checkUser")
    public String checkUser(@ModelAttribute User u) {
        List<User> lista = this.userService.getAllUsers();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        for(int i=0; i<lista.size(); i++){
            if((lista.get(i).getEmail().equals(u.getEmail())) && (lista.get(i).getPassword().equals(u.getPassword()))){
                if(lista.get(i).getIsAdmin()){
                    session.setAttribute("accessLevel", 2);
                    return "redirect:/displayAdmin";
                }
                else{
                    session.setAttribute("accessLevel", 1);
                    return "redirect:/homeUser";
                }
            }
        }
        session.setAttribute("accessLevel", 0);
        return "redirect:/displayViewer";
    }

    @GetMapping("/displayAdmin")
    public String opcoesAdmin(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            String[] opcoes = {"Registar Utilizador", "Listar Utilizadores",
                            "Criar Equipa", "Listar Equipas",
                            "Inserir Jogador", "Listar Jogadores",
                            "Introduzir Jogo", "Acompanhar Jogo", "Criar Eventos", "Estatísticas"};

            m.addAttribute("user", new User());
            m.addAttribute("opcoes", opcoes);
            return "displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/checkOptionAdmin")
    public String checkOptionAdmin(@ModelAttribute User u) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            switch (u.getOpcao()){
                case "Registar Utilizador":
                    return "redirect:/registaUtilizador";
                case "Listar Utilizadores":
                    return "redirect:/listaUtilizadores";
                case "Criar Equipa":
                    return "redirect:/criaEquipa";
                case "Listar Equipas":
                    return "redirect:/listaEquipas";
                case "Inserir Jogador":
                    return "redirect:/insereJogador";
                case "Listar Jogadores":
                    return "redirect:/listaJogadores";
                case "Introduzir Jogo":
                    return "redirect:/introduzJogo";
                case "Acompanhar Jogo":
                    return "redirect:/escolheJogo";
                case "Criar Eventos":
                    return "redirect:/displayUser";
                case "Estatísticas":
                    return "redirect:/detalhesEquipas";
            }

            return "redirect:/login";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/registaUtilizador")
    public String registaUtilizador(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("user", new User());
            return "registaUtilizador";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/guardaUtilizador")
    public String guardaUtilizador(@ModelAttribute User u) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            this.userService.addUser(u);
            return "redirect:/displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/listaUtilizadores")
    public String listaUtilizadores(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("users", this.userService.getAllUsers());

            return "listaUtilizadores";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("/insereJogador")
    public String insereJogador(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("jogador", new Jogador());
            m.addAttribute("allEquipas", this.equipaService.getAllTeams());
            return "insereJogador";
        }
        else{
            return "redirect:/";
        }

    }

    @PostMapping("/guardaJogador")
    public String guardaJogador(@ModelAttribute Jogador j) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            this.jogadorService.addPlayer(j);
            return "redirect:/displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/listaJogadores")
    public String listaJogadores(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("jogadores", this.jogadorService.getAllPlayers());

            return "listaJogadores";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("/criaEquipa")
    public String criaEquipa(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("equipa", new Equipa());
            return "criaEquipa";
        }
        else{
            return "redirect:/";
        }

    }

    @PostMapping("/guardaEquipa")
    public String guardaEquipa(@ModelAttribute Equipa e) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            e.setVitorias(0);
            e.setEmpates(0);
            e.setDerrotas(0);

            this.equipaService.addTeam(e);

            return "redirect:/displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/listaEquipas")
    public String listaEquipas(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("equipas", this.equipaService.getAllTeams());

            return "listaEquipas";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("/introduzJogo")
    public String introduzJogo(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            m.addAttribute("jogo", new Jogo());
            m.addAttribute("allEquipas", this.equipaService.getAllTeams());

            return "introduzJogo";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/guardaJogo")
    public String guardaJogo(@ModelAttribute Jogo j) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            this.jogoService.addGame(j);

            return "redirect:/displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/homeUser")
    public String homeUser(Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            String[] opcoes = {"Acompanhar Jogo", "Criar Eventos", "Estatísticas"};

            m.addAttribute("user", new User());
            m.addAttribute("opcoes", opcoes);
            return "displayAdmin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/displayUser")
    public String opcoesUser(Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            m.addAttribute("jogo", new Jogo());
            m.addAttribute("allJogos", this.jogoService.getAllGames());

            return "displayUser";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("/displayEventos")
    public String displayEventos(@ModelAttribute Jogo j, Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            String[] opcoes = {"Inicio do Jogo", "Fim do jogo", "Golo",
                    "Cartao Amarelo", "Cartao Vermelho",
                    "Jogo Interrompido", "Jogo Resumido"};

            SuperClassEventos eventos = new SuperClassEventos();
            eventos.setJogo(j);

            m.addAttribute("opcoes", opcoes);
            m.addAttribute("evento", eventos);
            m.addAttribute("jogo", j);

            return "displayEventos";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/checkOptionUser")
    public String checkOptionUser(@ModelAttribute SuperClassEventos e, Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            switch (e.getTipo()) {
                case "Golo":
                    return reportaGolo(e.getJogo(), m);
                case "Cartao Amarelo":
                    return reportaCartaoAmarelo(e.getJogo(), m);
                case "Cartao Vermelho":
                    return reportaCartaoVermelho(e.getJogo(), m);
                case "Inicio do Jogo":
                    return reportaInicio(e.getJogo(), m);
                case "Fim do jogo":
                    return reportaFim(e.getJogo(), m);
                case "Jogo Interrompido":
                    return reportaInterropido(e.getJogo(), m);
                case "Jogo Resumido":
                    return reportaResumido(e.getJogo(), m);
            }

            return "redirect:/login";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("reportaInicio/{id}")
    public String reportaInicio(@PathVariable ("id") Jogo j, Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            List<Ocorrencia> last_occur_list = j.getOcorrencias();
            if (last_occur_list.isEmpty()){
                Ocorrencia oc = new Ocorrencia(j, "Início de Jogo");
                m.addAttribute("oc", oc);
                return "reportaOcorrencia";
            }
            
            return "failedOcorrencia";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("reportaInterropido/{id}")
    public String reportaInterropido(@PathVariable ("id") Jogo j, Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            List<Ocorrencia> last_occur_list = j.getOcorrencias();
            if (!last_occur_list.isEmpty()){
                if (last_occur_list.get(last_occur_list.size()-1).getTipo().equals("Início de Jogo")){
                    Ocorrencia oc = new Ocorrencia(j, "Jogo Interrompido");
                    m.addAttribute("oc", oc);
                    return "reportaOcorrencia";
                }
            }
            
            return "failedOcorrencia";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("reportaResumido/{id}")
    public String reportaResumido(@PathVariable ("id") Jogo j, Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            List<Ocorrencia> last_occur_list = j.getOcorrencias();
            if (!last_occur_list.isEmpty()){
                if (last_occur_list.get(last_occur_list.size()-1).getTipo().equals("Jogo Interrompido")){
                    Ocorrencia oc = new Ocorrencia(j, "Jogo Resumido");
                    m.addAttribute("oc", oc);
                    return "reportaOcorrencia";
                }
            }
            
            return "failedOcorrencia";
        }
        else{
            return "redirect:/";
        }
    }


    @GetMapping("reportaFim/{id}")
    public String reportaFim(@PathVariable ("id") Jogo j, Model m){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            List<Ocorrencia> last_occur_list = j.getOcorrencias();
            if (!last_occur_list.isEmpty()){
                if (last_occur_list.get(last_occur_list.size()-1).getTipo().equals("Jogo Resumido")){
                    Ocorrencia oc = new Ocorrencia(j, "Fim de Jogo");
                    m.addAttribute("oc", oc);
                    return "reportaOcorrencia";
                } 
            } 
            
            return "failedOcorrencia";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/guardaOcorrencia")
    public String guardaOcorrencia(@ModelAttribute Ocorrencia o){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            ocorrenciaService.addOccurrence(o);
            o.getJogo().getOcorrencias().add(o);
            jogoService.addGame(o.getJogo());

            if (o.getTipo().equals("Fim de Jogo")){
                Equipa A = o.getJogo().getEquipas().get(0);
                int resA = o.getJogo().getCurrGolosEquipaCasa();
                Equipa B = o.getJogo().getEquipas().get(1);
                int resB = o.getJogo().getCurrGolosEquipaFora();

                if (resA > resB){
                    A.setVitorias(A.getVitorias()+1);
                    B.setDerrotas(B.getDerrotas()+1);
                }
                if (resB > resA){
                    B.setVitorias(B.getVitorias()+1);
                    A.setDerrotas(A.getDerrotas()+1);
                }
                if (resA == resB){
                    A.setEmpates(A.getEmpates()+1);
                    B.setEmpates(B.getEmpates()+1);
                }

                equipaService.addTeam(A);
                equipaService.addTeam(B);
            }
            return "redirect:/displayUser";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/reportaGolo/{id}")
    public String reportaGolo(@PathVariable ("id") Jogo jogo, Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            if (jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Início de Jogo") ||
            jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Jogo Resumido")){
                Golo golo = new Golo();
                golo.setJogo(jogo);
        
        
                m.addAttribute("golo", golo);
                m.addAttribute("jogo", jogo);
        
                List<Jogador> allJogadores = jogo.getEquipas().get(0).getJogadoresEquipa();
                allJogadores.addAll(jogo.getEquipas().get(1).getJogadoresEquipa());
        
                m.addAttribute("allJogadores", allJogadores);
        
                return "reportaGolo";
            }
            else{
                return "failedGolo";
            }
        }
        else{
            return "redirect:/";
        }
        
    }

    @PostMapping("/guardaGolo")
    public String guardaGolo(@ModelAttribute Golo g) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            int count = 0, count2 = 0;

            for(int i=0; i < g.getMarcador().getCartoesJogador().size(); i++){
                if(g.getMarcador().getCartoesJogador().get(i).getTipo().equals("Cartao Vermelho")){
                    count++;
                }

                if(g.getMarcador().getCartoesJogador().get(i).getTipo().equals("Cartao Amarelo")){
                    count2++;
                }
            }

            if(g.getJogo().getEquipas().get(0).getNome().equals(g.getMarcador().getEquipa().getNome()) && count == 0 && count2 < 2){
                goloService.addGoal(g);
                g.getJogo().getGolos().add(g);

                g.getJogo().setCurrGolosEquipaCasa(g.getJogo().getCurrGolosEquipaCasa() + 1);

                jogoService.addGame(g.getJogo());
            } else if (g.getJogo().getEquipas().get(1).getNome().equals(g.getMarcador().getEquipa().getNome()) && count == 0 && count2 < 2){
                goloService.addGoal(g);
                g.getJogo().getGolos().add(g);

                g.getJogo().setCurrGolosEquipaFora(g.getJogo().getCurrGolosEquipaFora() + 1);

                jogoService.addGame(g.getJogo());
            }
            return "redirect:/displayUser";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/reportaCartaoAmarelo/{id}")
    public String reportaCartaoAmarelo(@PathVariable ("id") Jogo jogo, Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            if (jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Início de Jogo") ||
            jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Jogo Resumido")){
                Cartao cartaoA = new Cartao();
                cartaoA.setJogo(jogo);


                m.addAttribute("cartaoA", cartaoA);
                m.addAttribute("jogo", jogo);

                List<Jogador> allJogadores = jogo.getEquipas().get(0).getJogadoresEquipa();
                allJogadores.addAll(jogo.getEquipas().get(1).getJogadoresEquipa());

                m.addAttribute("allJogadores", allJogadores);


                return "reportaCartaoAmarelo";
            }
            else{
                return "failedGolo";
            }
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/guardaCartaoAmarelo")
    public String guardaCrataoAmarelo(@ModelAttribute Cartao cartaoA) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            int count = 0;

            cartaoA.setTipo("Cartao Amarelo");

            for(int i=0; i < cartaoA.getJogador().getCartoesJogador().size(); i++){
                if(cartaoA.getJogador().getCartoesJogador().get(i).getTipo().equals("Cartao Vermelho")){
                    count++;
                }
            }

            if(count == 0 && cartaoA.getJogador().getCartoesJogador().size() < 2){
                cartaoA.getJogo().getCartoes().add(cartaoA);
                cartaoA.getJogador().getCartoesJogador().add(cartaoA);

                cartaoService.addCard(cartaoA);

            }
            return "redirect:/displayUser";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/reportaCartaoVermelho/{id}")
    public String reportaCartaoVermelho(@PathVariable ("id") Jogo jogo, Model m) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            if (jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Início de Jogo") ||
            jogo.getOcorrencias().get(jogo.getOcorrencias().size()-1).getTipo().equals("Jogo Resumido")){
                Cartao cartaoV = new Cartao();
                cartaoV.setJogo(jogo);


                m.addAttribute("cartaoV", cartaoV);
                m.addAttribute("jogo", jogo);

                List<Jogador> allJogadores = jogo.getEquipas().get(0).getJogadoresEquipa();
                allJogadores.addAll(jogo.getEquipas().get(1).getJogadoresEquipa());

                m.addAttribute("allJogadores", allJogadores);


                return "reportaCartaoVermelho";
            } 
            else{
                return "failedGolo";
            }
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/guardaCartaoVermelho")
    public String guardaCrataoVermelho(@ModelAttribute Cartao cartaoV) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") >= 1){
            int count = 0, count2 = 0;

            cartaoV.setTipo("Cartao Vermelho");


            for(int i=0; i < cartaoV.getJogador().getCartoesJogador().size(); i++){
                if(cartaoV.getJogador().getCartoesJogador().get(i).getTipo().equals("Cartao Vermelho")){
                    count++;
                }

                if(cartaoV.getJogador().getCartoesJogador().get(i).getTipo().equals("Cartao Amarelo")){
                    count2++;
                }
            }

            if(count == 0 && count2 < 2){
                cartaoV.getJogo().getCartoes().add(cartaoV);
                cartaoV.getJogador().getCartoesJogador().add(cartaoV);

                cartaoService.addCard(cartaoV);

            }

            return "redirect:/displayUser";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/displayViewer")
    public void opcoesViewer(Model m) {
        String[] opcoes = {"Acompanhar Jogo", "Estatisticas"};

        m.addAttribute("user", new User());
        m.addAttribute("opcoes", opcoes);

    }

    @PostMapping("/checkOptionViewer")
    public String checkOptionViewer(@ModelAttribute User u) {

        switch (u.getOpcao()) {
            case "Acompanhar Jogo":
                return "redirect:/escolheJogo";
            case "Estatisticas":
                return "redirect:/detalhesEquipas";
        }

        return "redirect:/login";
    }

    @GetMapping("/escolheJogo")
    public String detalhesJogo(Model m) {
        m.addAttribute("jogo", new Jogo());

        m.addAttribute("allJogos", this.jogoService.getAllGames());
        return "escolheJogo";
    }

    @GetMapping("/detalhesJogo")
    public String detalhesJogo(@ModelAttribute Jogo j, Model m) {

        Optional<Jogo> aux = this.jogoService.getGame(j.getId());

        if(aux.isPresent()){
            m.addAttribute("jogo", aux.get());
        }
        else{
            return "redirect:/escolheJogo";
        }
        return "detalhesJogo";
    }

    @GetMapping("/detalhesEquipas")
    public String detalhesEquipas(Model m) {
        m.addAttribute("equipa", equipaService.getAllTeams());
        m.addAttribute("j", jogadorService.getPlayer(goloService.getBestScorer()).get());

        return "detalhesEquipas";
    }

    @PostMapping("/insertPLTeams")
    public String insertPLTeams(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            try {
                JSONArray resp = Unirest.get("https://v3.football.api-sports.io/teams?league=39&season=2021")
                        .header("x-rapidapi-host", "v3.football.api-sports.io")
                        .header("x-rapidapi-key", "5655fb774ffe8e4605427c298b2df1b5")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONArray("response");

                for(int i = 0; i < resp.length(); i++){
                    String teamName = resp.getJSONObject(i).getJSONObject("team").getString("name");
                    String teamLogo = resp.getJSONObject(i).getJSONObject("team").getString("logo");

                    Equipa e = new Equipa();
                    e.setImagem(teamLogo);
                    e.setNome(teamName);
                    if (equipaService.getTeam(teamName) == null){
                        equipaService.addTeam(e);
                    }
                }

            } catch (UnirestException e) {
                e.printStackTrace();
            }

            return "redirect:/listaEquipas";
        }
        else{
            return "redirect:/";
        }
    }

    @PostMapping("/insertPLPlayers")
    public String insertPLPlayers(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        if ((Integer) session.getAttribute("accessLevel") != null && (Integer) session.getAttribute("accessLevel") == 2){
            try {
                for(int page_num = 1; page_num <=40; page_num++){
                    JSONArray resp = Unirest.get("https://v3.football.api-sports.io/players?league=39&season=2021&page="+ page_num )
                            .header("x-rapidapi-host", "v3.football.api-sports.io")
                            .header("x-rapidapi-key", "5655fb774ffe8e4605427c298b2df1b5")
                            .asJson()
                            .getBody()
                            .getObject()
                            .getJSONArray("response");

                    for(int i = 0; i < resp.length(); i++){
                        Jogador j = new Jogador();

                        String playerName = resp.getJSONObject(i).getJSONObject("player").getString("name");
                        j.setNome(playerName);
                        String playerPosition = resp.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("games").getString("position");
                        j.setPosicao(playerPosition);
                        if (!resp.getJSONObject(i).getJSONObject("player").getJSONObject("birth").isNull("date")){
                            String playerDOB = resp.getJSONObject(i).getJSONObject("player").getJSONObject("birth").getString("date");
                            Date dob = new Date();
                            dob.setYear(Integer.parseInt(playerDOB.substring(0, 4)) - 1900);
                            dob.setMonth(Integer.parseInt(playerDOB.substring(5, 7)) - 1);
                            dob.setDate(Integer.parseInt(playerDOB.substring(8)));
                            j.setData_nascimento(dob);
                        }
                        String playerTeam = resp.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("team").getString("name");
                        Equipa eq = equipaService.getTeam(playerTeam);
                        j.setEquipa(eq);

                        if (jogadorService.getPlayer(playerName) == null || (jogadorService.getPlayer(playerName).getEquipa().getNome() != playerTeam)){
                            jogadorService.addPlayer(j);
                        }

                    }
                    if (page_num % 9 == 0){
                        Thread.sleep(60*1000);
                    }
                    System.out.println("Gathering Players (Page " + page_num + ")...");
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "redirect:/listaJogadores";
        }
        else{
            return "redirect:/";
        }
    }
}