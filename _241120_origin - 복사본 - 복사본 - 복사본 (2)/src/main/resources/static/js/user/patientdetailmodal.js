// 수정 모달 열기
function openEditModal(button) {
    // 버튼에서 환자 정보를 가져옴
    var patientId = button.getAttribute('data-patient-id');
    var patientName = button.getAttribute('data-patient-name');
    var patientPhone = button.getAttribute('data-patient-phone');
    var patientEmail = button.getAttribute('data-patient-email');
    var patientGender = button.getAttribute('data-patient-gender');

    // 모달에 값을 설정
    document.getElementById('editPatientId').value = patientId;
    document.getElementById('editName').value = patientName;
    document.getElementById('editPhone').value = patientPhone;
    document.getElementById('editEmail').value = patientEmail;
    document.getElementById('editGender').value = patientGender;

    // 모달 열기
    document.getElementById('patientEditModal').style.display = 'block';
}

// 수정 모달 닫기
document.getElementById('closeEditModal').onclick = function() {
    document.getElementById('patientEditModal').style.display = 'none';
}

// 수정 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    var modal = document.getElementById('patientEditModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}