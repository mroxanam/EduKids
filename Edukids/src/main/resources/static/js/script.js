document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');

    // Función para cerrar el modal
    function cerrarModal() {
        const modal = document.querySelector('.success-modal');
        if (modal) {
            modal.style.display = 'none';
        }
    }

    // Asignar la función al ámbito global para que el HTML pueda llamarla
    window.cerrarModal = cerrarModal;

    // Validación básica en cliente
    function validarFormulario() {
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        if (!email || !password) {
            Swal.fire({
                title: 'Campos incompletos',
                text: 'Por favor completa todos los campos',
                icon: 'error',
                confirmButtonText: 'Entendido'
            });
            return false;
        }

        // Validación simple de email
        if (!/^\S+@\S+\.\S+$/.test(email)) {
            Swal.fire({
                title: 'Email inválido',
                text: 'Por favor ingresa un email válido',
                icon: 'error',
                confirmButtonText: 'Entendido'
            });
            return false;
        }

        return true;
    }

    // Manejar el envío del formulario
    if (form) {
        form.addEventListener('submit', function(e) {
            // Prevenir envío por defecto
            e.preventDefault();

            // Validar formulario
            if (!validarFormulario()) {
                return;
            }

            // Mostrar loader mientras se procesa
            Swal.fire({
                title: 'Registrando...',
                text: 'Por favor espera un momento',
                allowOutsideClick: false,
                didOpen: () => {
                    Swal.showLoading();
                }
            });

            // Enviar formulario manualmente
            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData,
                headers: {
                    'Accept': 'text/html'
                }
            })
            .then(response => response.text())
            .then(html => {
                // Reemplazar el contenido actual con la nueva respuesta
                document.documentElement.innerHTML = html;

                // Mostrar modal de éxito si está presente en la respuesta
                const successModal = document.querySelector('.success-modal');
                if (successModal && successModal.style.display !== 'none') {
                    // Cerrar SweetAlert de carga
                    Swal.close();
                }
            })
            .catch(error => {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al registrar: ' + error.message,
                    icon: 'error',
                    confirmButtonText: 'Entendido'
                });
            });
        });
    }
});