const form = document.querySelector('#contactForm');
const feedback = document.querySelector('#formFeedback');

form?.addEventListener('submit', (event) => {
  event.preventDefault();

  const formData = new FormData(form);
  const name = formData.get('name')?.toString().trim();
  const email = formData.get('email')?.toString().trim();
  const role = formData.get('role')?.toString().trim();
  const topic = formData.get('topic')?.toString().trim();
  const message = formData.get('message')?.toString().trim();

  if (!name || !email || !role || !topic || !message) {
    feedback.textContent = 'Preencha todos os campos para enviar o pedido.';
    feedback.style.color = '#ffb4b4';
    return;
  }

  feedback.textContent = `Pedido enviado com sucesso para ${topic.toLowerCase()}. A equipa irá responder para ${email}.`;
  feedback.style.color = '#9effd7';
  form.reset();
});
