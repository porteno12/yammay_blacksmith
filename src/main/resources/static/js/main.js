document.addEventListener('DOMContentLoaded', function() {
    console.log('Artisan Iron app loaded');

    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function() {
            const submitBtn = form.querySelector('button[type="submit"]');
            if (submitBtn) {
                submitBtn.disabled = true;
                submitBtn.textContent = 'שליחה...';
            }
        });
    });

    const imageLinks = document.querySelectorAll('.product-image img');
    imageLinks.forEach(img => {
        img.addEventListener('error', function() {
            this.src = '/images/placeholder.jpg';
        });
    });
});
