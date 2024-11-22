document.addEventListener('DOMContentLoaded', function() {
    const slides = document.querySelectorAll('.slide');
    const dots = document.querySelectorAll('.dot');
    let currentIndex = 0;
    let slideInterval;

    function showSlide(index) {
        slides[currentIndex].classList.remove('active');
        dots[currentIndex].classList.remove('active');
        currentIndex = index;
        slides[currentIndex].classList.add('active');
        dots[currentIndex].classList.add('active');
    }

    function showNextImage() {
        let nextIndex = (currentIndex + 1) % slides.length;
        showSlide(nextIndex);
    }

    dots.forEach(dot => {
        dot.addEventListener('click', function() {
            let index = parseInt(this.getAttribute('data-index'));
            clearInterval(slideInterval);
            showSlide(index);
            slideInterval = setInterval(showNextImage, 5000);
        });
    });

    slideInterval = setInterval(showNextImage, 5000); // 5초마다 다음 이미지로 전환
});

