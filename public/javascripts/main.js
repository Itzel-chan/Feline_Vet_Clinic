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