// 디테일 버튼 클릭 시 모달 내용 로드 및 모달 열기
document.querySelectorAll('.detailModal').forEach(button => {
    button.addEventListener('click', function() {
        const patientId = this.getAttribute('data-patient-id');

        // 여기서 서버나 API 호출을 통해 환자 정보 및 예약 정보를 가져옵니다.
        fetch(`/patient/detail/${patientId}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('patient-name').innerText = data.name;
                document.getElementById('patient-phone').innerText = data.phonenumber;
                document.getElementById('patient-email').innerText = data.email;
                document.getElementById('patient-gender').innerText = data.gender;
                document.getElementById('patient-created_at').innerText = data.created_at;
                document.getElementById('patient-updated_at').innerText = data.updated_at;

                // 예약 정보 로드 (만약 예약 정보가 있다면 표시)
                const appointmentsContainer = document.getElementById('patient-appointments');
                if (data.reservations && data.reservations.length > 0) {
                    appointmentsContainer.innerHTML = data.reservations.map(reservation => `
              <p>예약 날짜: ${reservation.reservation_date} ${reservation.hour_select}:${reservation.minutes_select} <br>
              치료 항목1: ${reservation.treatment1} <br>
              치료 항목2: ${reservation.treatment2} <br>
              상태: ${reservation.status}</p>
            `).join('');
                } else {
                    appointmentsContainer.innerHTML = '<p>예약 정보가 없습니다.</p>';
                }

                // 환자 정보 수정 폼에 현재 정보를 채우기
                document.getElementById('update-name').value = data.name;
                document.getElementById('update-phone').value = data.phonenumber;
                document.getElementById('update-email').value = data.email;
                document.getElementById('update-gender').value = data.gender;

                // 모달 띄우기
                document.getElementById('patientdetailModal').style.display = 'block';
            });
    });
});

// 모달 닫기
document.getElementById('closepatientdetailModal').addEventListener('click', function() {
    document.getElementById('patientdetailModal').style.display = 'none';
});