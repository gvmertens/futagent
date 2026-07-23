const matches = [
  { competition: "Brasileirão", teams: "Palmeiras × Flamengo", time: "Dom, 18:30", score: "96%" },
  { competition: "Champions League", teams: "Real Madrid × Liverpool", time: "Qua, 16:00", score: "91%" },
  { competition: "Paulistão", teams: "Corinthians × Santos", time: "Sáb, 20:00", score: "84%" },
];

export function App() {
  return (
    <main>
      <nav><span className="ball">F</span><strong>Futagend</strong><button className="profile">GL</button></nav>
      <section className="hero">
        <p className="eyebrow">SUA SEMANA NO FUTEBOL</p>
        <h1>Os jogos certos.<br /><em>No seu tempo.</em></h1>
        <p className="lede">O assistente que entende o que você acompanha, explica por que uma partida importa e cuida da sua agenda.</p>
        <div className="ask"><span>✦</span><input aria-label="Pergunte ao Futagend" placeholder="O que vale a pena assistir esta semana?" /><button>Enviar</button></div>
      </section>
      <section className="content">
        <header><div><p className="eyebrow">RECOMENDADO PARA VOCÊ</p><h2>Partidas imperdíveis</h2></div><a href="#agenda">Ver agenda →</a></header>
        <div className="grid">
          {matches.map((match, index) => <article key={match.teams}>
            <div className="rank">0{index + 1}</div><span>{match.competition}</span><h3>{match.teams}</h3>
            <footer><time>{match.time}</time><b>{match.score} match</b></footer>
          </article>)}
        </div>
      </section>
    </main>
  );
}

