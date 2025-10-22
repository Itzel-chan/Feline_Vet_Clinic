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
});