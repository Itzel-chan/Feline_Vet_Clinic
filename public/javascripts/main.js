document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.querySelector('.menu-toggle');
    const navList = document.querySelector('.nav-list');
    const navLinks = document.querySelectorAll('.nav-link');

    // Funcionalidade para o menu responsivo (mobile)
    menuToggle.addEventListener('click', function() {
        navList.classList.toggle('active');
        menuToggle.classList.toggle('active'); // Para animar o ícone do menu
    });

    // Fechar o menu ao clicar em um link (apenas em mobile)
    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            if (window.innerWidth <= 768) { // Checa se está em mobile
                navList.classList.remove('active');
                menuToggle.classList.remove('active');
            }
            // Opcional: Adicionar classe 'active' ao link clicado para destaque
            navLinks.forEach(otherLink => otherLink.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Opcional: Destacar o link da página atual ao carregar
    const currentPath = window.location.pathname;
    navLinks.forEach(link => {
        // Exemplo simples: Se o href do link está contido no caminho atual
        // Você pode precisar de uma lógica mais robusta dependendo das suas rotas Play
        if (link.getAttribute('href') && currentPath.includes(link.getAttribute('href').substring(1))) {
            link.classList.add('active');
        }
    });
});

    document.addEventListener('DOMContentLoaded', function() {
        const serviceCards = document.querySelectorAll('.service-card');

        serviceCards.forEach(card => {
            card.addEventListener('click', function() {
                // Oculta qualquer descrição que esteja aberta
                document.querySelectorAll('.service-card.expanded').forEach(openCard => {
                    if (openCard !== this) { // Se for um card diferente do que foi clicado
                        openCard.classList.remove('expanded');
                    }
                });

                // Alterna a classe 'expanded' no card clicado
                this.classList.toggle('expanded');
            });
        });
    });


document.addEventListener('DOMContentLoaded', function() {
    const userBtn = document.querySelector('.icon-user-btn');
    const closeBtn = document.querySelector('.close-btn');
    const userSidebar = document.getElementById('user-sidebar');

    // Define a largura com base no tamanho da tela
    function getSidebarWidth() {
        return window.innerWidth <= 768 ? '80vw' : '25vw';
    }

    // Abre a sidebar e posiciona a patinha ao lado
    userBtn.addEventListener('click', function() {
        const sidebarWidth = getSidebarWidth();
        const patinhaGap = '0px';

        userSidebar.style.width = sidebarWidth;
        closeBtn.style.display = 'block';
        closeBtn.style.right = `calc(${sidebarWidth} + ${patinhaGap})`;
    });

    // Fecha a sidebar e esconde a patinha
    closeBtn.addEventListener('click', function() {
        userSidebar.style.width = '0';
        closeBtn.style.display = 'none';
    });

    // Fecha a sidebar ao clicar fora dela
    document.addEventListener('click', function(event) {
        if (!userSidebar.contains(event.target) && !userBtn.contains(event.target) && !closeBtn.contains(event.target)) {
            userSidebar.style.width = '0';
            closeBtn.style.display = 'none';
        }
    });
});




// Página CuidadosPet

/**
 * Abre o modal especificado pelo ID, adicionando a classe 'active'.
 * @param {string} modalId - O ID do elemento modal-overlay a ser aberto.
 */
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add('active');
        // Impede o scroll da página enquanto o modal estiver aberto
        document.body.style.overflow = 'hidden'; 
    } else {
        console.error('Modal não encontrado com o ID:', modalId);
    }
}

/**
 * Fecha o modal especificado pelo ID, removendo a classe 'active'.
 * @param {string} modalId - O ID do elemento modal-overlay a ser fechado.
 */
function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove('active');
        // Restaura o scroll da página
        document.body.style.overflow = 'auto';
    }
}

// Opcional: Fechar o modal clicando no overlay escuro (fora do conteúdo)
document.addEventListener('click', function(event) {
    // Verifica se o elemento clicado possui a classe 'modal-overlay' E se está visível
    if (event.target.classList.contains('modal-overlay')) {
        closeModal(event.target.id);
    }



    

    document.addEventListener('DOMContentLoaded', function() {
    const pricingCards = document.querySelectorAll('.pricing-card');

    pricingCards.forEach(card => {
        const highlightColor = card.getAttribute('data-highlight-color');
        const icon = card.querySelector('.pricing-icon');
        const button = card.querySelector('.btn-pricing');

        // Garante que o botão tenha a cor de destaque inicial
        button.style.backgroundColor = highlightColor;

        card.addEventListener('mouseenter', () => {
            card.style.borderColor = highlightColor; // Borda do card
            icon.style.color = highlightColor; // Cor do ícone
            
            // Botão: um tom um pouco mais escuro/saturado no hover do card
            button.style.backgroundColor = darkenColor(highlightColor, -10); // Escurece um pouco
            button.style.boxShadow = `0 8px 20px ${highlightColor}60`; // Sombra colorida
        });

        card.addEventListener('mouseleave', () => {
            card.style.borderColor = 'transparent'; // Volta a borda transparente
            icon.style.color = 'var(--text-muted)'; // Volta a cor do ícone
            
            // Botão volta à cor normal
            button.style.backgroundColor = highlightColor;
            button.style.boxShadow = 'none';
        });

        // Efeito de hover direto no botão para um toque extra
        button.addEventListener('mouseenter', () => {
            button.style.transform = 'translateY(-3px) scale(1.02)'; // Escala um pouco mais
            button.style.boxShadow = `0 10px 25px ${highlightColor}70`;
        });
        button.addEventListener('mouseleave', () => {
            button.style.transform = 'translateY(0) scale(1)';
            button.style.boxShadow = `0 8px 20px ${highlightColor}60`; // Volta à sombra do card hover
        });
    });

    // Função auxiliar para escurecer/clarear uma cor hexadecimal
    function darkenColor(hex, percent) {
        var f = parseInt(hex.slice(1), 16),
            t = percent < 0 ? 0 : 255,
            p = percent < 0 ? percent * -1 : percent,
            R = f >> 16,
            G = (f >> 8) & 0x00ff,
            B = f & 0x0000ff;
        return (
            "#" +
            (
                0x1000000 +
                (Math.round((t - R) * p) + R) * 0x10000 +
                (Math.round((t - G) * p) + G) * 0x100 +
                (Math.round((t - B) * p) + B)
            )
            .toString(16)
            .slice(1)
        );
    }
});







// Arquivo filtro.js

document.addEventListener('DOMContentLoaded', () => {
    // Seleciona todos os elementos com a classe 'filtro'
    const filterItems = document.querySelectorAll('.filtro');

    // Inicialmente, define o item "TODAS" como ativo por padrão
    const todasElement = document.getElementById('TODAS');
    if (todasElement) {
        todasElement.classList.add('ativo');
    }

    // Adiciona um ouvinte de evento 'click' a cada item de filtro
    filterItems.forEach(item => {
        item.addEventListener('click', (event) => {
            
            // 1. Remove a classe 'ativo' de todos os itens
            filterItems.forEach(i => {
                i.classList.remove('ativo');
            });
            
            // 2. Adiciona a classe 'ativo' no item clicado
            event.currentTarget.classList.add('ativo');

            // 3. (OPCIONAL) Aqui você pode chamar sua função de filtragem no backend/frontend,
            //    usando o ID do elemento clicado para saber qual status filtrar.
            const selectedStatus = event.currentTarget.id;
            console.log(`Filtrando consultas por: ${selectedStatus}`);
        });
    });
});

});