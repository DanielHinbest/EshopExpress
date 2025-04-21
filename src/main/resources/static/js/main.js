// Document ready function
document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Auto-dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
    alerts.forEach(function(alert) {
        setTimeout(function() {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // Quantity controls in cart
    setupQuantityControls();

    // Form validation
    setupFormValidation();

    // Image preview for admin forms
    setupImagePreview();
});

// Setup quantity controls in cart
function setupQuantityControls() {
    const quantityControls = document.querySelectorAll('.quantity-control');
    quantityControls.forEach(function(control) {
        const decrementBtn = control.querySelector('.decrement');
        const incrementBtn = control.querySelector('.increment');
        const input = control.querySelector('input');
        const form = control.closest('form');

        if (decrementBtn && incrementBtn && input && form) {
            decrementBtn.addEventListener('click', function() {
                const currentValue = parseInt(input.value);
                if (currentValue > 1) {
                    input.value = currentValue - 1;
                    form.submit();
                }
            });

            incrementBtn.addEventListener('click', function() {
                const currentValue = parseInt(input.value);
                if (currentValue < 10) {  // Max quantity limit
                    input.value = currentValue + 1;
                    form.submit();
                }
            });
        }
    });
}

// Setup form validation
function setupFormValidation() {
    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
}

// Setup image preview for admin forms
function setupImagePreview() {
    const imageInput = document.getElementById('coverImageUpload');
    const imagePreview = document.getElementById('imagePreview');

    if (imageInput && imagePreview) {
        imageInput.addEventListener('change', function() {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    imagePreview.src = e.target.result;
                    imagePreview.style.display = 'block';
                }
                reader.readAsDataURL(file);
            }
        });
    }
}

// Search autocomplete
function setupSearchAutocomplete() {
    const searchInput = document.querySelector('input[name="query"]');
    const resultsContainer = document.getElementById('searchResults');

    if (searchInput && resultsContainer) {
        searchInput.addEventListener('input', debounce(function() {
            const query = this.value.trim();
            if (query.length < 2) {
                resultsContainer.innerHTML = '';
                return;
            }

            fetch(`/api/search/autocomplete?query=${encodeURIComponent(query)}`)
                .then(response => response.json())
                .then(data => {
                    resultsContainer.innerHTML = '';
                    if (data.length > 0) {
                        data.forEach(item => {
                            const div = document.createElement('div');
                            div.className = 'search-result-item';
                            div.innerHTML = `
                                <a href="/games/${item.id}">
                                    <img src="${item.imageUrl}" alt="${item.title}">
                                    <span>${item.title}</span>
                                </a>
                            `;
                            resultsContainer.appendChild(div);
                        });
                        resultsContainer.style.display = 'block';
                    } else {
                        resultsContainer.style.display = 'none';
                    }
                })
                .catch(error => {
                    console.error('Error fetching search results:', error);
                });
        }, 300));

        // Hide search results when clicking outside
        document.addEventListener('click', function(event) {
            if (!searchInput.contains(event.target) && !resultsContainer.contains(event.target)) {
                resultsContainer.style.display = 'none';
            }
        });
    }
}

// Debounce function to limit how often a function can fire
function debounce(func, wait) {
    let timeout;
    return function(...args) {
        const context = this;
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(context, args), wait);
    };
}