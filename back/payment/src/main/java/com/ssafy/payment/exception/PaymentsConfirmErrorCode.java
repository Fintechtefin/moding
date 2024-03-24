package com.ssafy.payment.exception;

import com.ssafy.payment.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentsConfirmErrorCode implements BaseErrorCode {
    ALREADY_PROCESSED_PAYMENT("PAYMENTS_CONFIRM_ALREADY_PROCESSED_PAYMENT", "이미 처리된 결제 입니다."),
    PROVIDER_ERROR("PAYMENTS_CONFIRM_PROVIDER_ERROR", "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    EXCEED_MAX_CARD_INSTALLMENT_PLAN(
            "PAYMENTS_CONFIRM_EXCEED_MAX_CARD_INSTALLMENT_PLAN", "설정 가능한 최대 할부 개월 수를 초과했습니다."),
    INVALID_REQUEST("PAYMENTS_CONFIRM_INVALID_REQUEST", "잘못된 요청입니다."),
    INVALID_API_KEY("PAYMENTS_CONFIRM_INVALID_API_KEY", "잘못된 시크릿키 연동 정보 입니다."),
    INVALID_REJECT_CARD("PAYMENTS_CONFIRM_INVALID_API_KEY", "카드 사용이 거절되었습니다. 카드사 문의가 필요합니다."),
    BELOW_MINIMUM_AMOUNT(
            "PAYMENTS_CONFIRM_BELOW_MINIMUM_AMOUNT",
            "신용카드는 결제금액이 100원 이상, 계좌는 200원이상부터 결제가 가능합니다."),
    INVALID_CARD_EXPIRATION("PAYMENTS_CONFIRM_INVALID_CARD_EXPIRATION", "카드 정보를 다시 확인해주세요. (유효기간)"),
    EXCEED_MAX_DAILY_PAYMENT_COUNT(
            "PAYMENTS_CONFIRM_EXCEED_MAX_DAILY_PAYMENT_COUNT", "하루 결제 가능 횟수를 초과했습니다."),
    NOT_SUPPORTED_INSTALLMENT_PLAN_CARD_OR_MERCHANT(
            "PAYMENTS_CONFIRM_NOT_SUPPORTED_INSTALLMENT_PLAN_CARD_OR_MERCHANT",
            "할부가 지원되지 않는 카드 또는 가맹점 입니다."),
    INVALID_CARD_INSTALLMENT_PLAN(
            "PAYMENTS_CONFIRM_INVALID_CARD_INSTALLMENT_PLAN", "할부 개월 정보가 잘못되었습니다."),
    NOT_SUPPORTED_MONTHLY_INSTALLMENT_PLAN(
            "PAYMENTS_CONFIRM_NOT_SUPPORTED_MONTHLY_INSTALLMENT_PLAN", "할부가 지원되지 않는 카드입니다."),
    EXCEED_MAX_PAYMENT_AMOUNT("PAYMENTS_CONFIRM_EXCEED_MAX_PAYMENT_AMOUNT", "하루 결제 가능 금액을 초과했습니다."),
    NOT_FOUND_TERMINAL_ID(
            "PAYMENTS_CONFIRM_NOT_FOUND_TERMINAL_ID", "단말기번호(Terminal Id)가 없습니다. 토스페이먼츠로 문의 바랍니다."),
    INVALID_AUTHORIZE_AUTH("PAYMENTS_CONFIRM_INVALID_AUTHORIZE_AUTH", "유효하지 않은 인증 방식입니다."),
    INVALID_CARD_LOST_OR_STOLEN("PAYMENTS_CONFIRM_INVALID_CARD_LOST_OR_STOLEN", "분실 혹은 도난 카드입니다."),
    INVALID_CARD_NUMBER("PAYMENTS_CONFIRM_INVALID_CARD_NUMBER", "카드번호를 다시 확인해주세요."),
    INVALID_UNREGISTERED_SUBMALL(
            "PAYMENTS_CONFIRM_INVALID_UNREGISTERED_SUBMALL",
            "등록되지 않은 서브몰입니다. 서브몰이 없는 가맹점이라면 안심클릭이나 ISP 결제가 필요합니다."),
    NOT_REGISTERED_BUSINESS("PAYMENTS_CONFIRM_NOT_REGISTERED_BUSINESS", "등록되지 않은 사업자 번호입니다."),
    EXCEED_MAX_ONE_DAY_WITHDRAW_AMOUNT(
            "PAYMENTS_CONFIRM_EXCEED_MAX_ONE_DAY_WITHDRAW_AMOUNT", "1일 출금 한도를 초과했습니다."),
    EXCEED_MAX_ONE_TIME_WITHDRAW_AMOUNT(
            "PAYMENTS_CONFIRM_EXCEED_MAX_ONE_TIME_WITHDRAW_AMOUNT", "1회 출금 한도를 초과했습니다."),
    CARD_PROCESSING_ERROR("PAYMENTS_CONFIRM_CARD_PROCESSING_ERROR", "카드사에서 오류가 발생했습니다."),
    EXCEED_MAX_AMOUNT("PAYMENTS_CONFIRM_EXCEED_MAX_AMOUNT", "거래금액 한도를 초과했습니다."),
    INVALID_ACCOUNT_INFO_RE_REGISTER(
            "PAYMENTS_CONFIRM_INVALID_ACCOUNT_INFO_RE_REGISTER", "유효하지 않은 계좌입니다. 계좌 재등록 후 시도해주세요."),
    UNAUTHORIZED_KEY("PAYMENTS_CONFIRM_UNAUTHORIZED_KEY", "인증되지 않은 시크릿 키 혹은 클라이언트 키 입니다."),
    REJECT_ACCOUNT_PAYMENT(
            "PAYMENTS_CONFIRM_INVALID_ACCOUNT_INFO_RE_REGISTER", "잔액부족으로 결제에 실패했습니다."),
    REJECT_CARD_PAYMENT("PAYMENTS_CONFIRM_REJECT_CARD_PAYMENT", "한도초과 혹은 잔액부족으로 결제에 실패했습니다."),
    REJECT_CARD_COMPANY("PAYMENTS_CONFIRM_REJECT_CARD_COMPANY", "결제 승인이 거절되었습니다."),
    FORBIDDEN_REQUEST("PAYMENTS_CONFIRM_FORBIDDEN_REQUEST", "허용되지 않은 요청입니다."),
    REJECT_TOSSPAY_INVALID_ACCOUNT(
            "PAYMENTS_CONFIRM_REJECT_TOSSPAY_INVALID_ACCOUNT",
            "선택하신 출금 계좌가 출금이체 등록이 되어 있지 않아요. 계좌를 다시 등록해 주세요."),
    EXCEED_MAX_AUTH_COUNT(
            "PAYMENTS_CONFIRM_EXCEED_MAX_AUTH_COUNT", "최대 인증 횟수를 초과했습니다. 카드사로 문의해주세요."),
    EXCEED_MAX_ONE_DAY_AMOUNT("PAYMENTS_CONFIRM_EXCEED_MAX_ONE_DAY_AMOUNT", "일일 한도를 초과했습니다."),
    NOT_AVAILABLE_BANK("PAYMENTS_CONFIRM_NOT_AVAILABLE_BANK", "은행 서비스 시간이 아닙니다."),
    INVALID_PASSWORD("PAYMENTS_CONFIRM_INVALID_PASSWORD", "결제 비밀번호가 일치하지 않습니다."),
    NOT_FOUND_PAYMENT("PAYMENTS_CONFIRM_NOT_FOUND_PAYMENT", "존재하지 않는 결제 정보 입니다."),
    NOT_FOUND_PAYMENT_SESSION(
            "PAYMENTS_CONFIRM_NOT_FOUND_PAYMENT_SESSION", "결제 시간이 만료되어 결제 진행 데이터가 존재하지 않습니다."),
    FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING(
            "PAYMENTS_CONFIRM_FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING",
            "결제가 완료되지 않았어요. 다시 시도해주세요."),
    FAILED_INTERNAL_SYSTEM_PROCESSING(
            "PAYMENTS_CONFIRM_FAILED_INTERNAL_SYSTEM_PROCESSING",
            "내부 시스템 처리 작업이 실패했습니다. 잠시 후 다시 시도해주세요."),
    UNKNOWN_PAYMENT_ERROR(
            "PAYMENTS_CONFIRM_UNKNOWN_PAYMENT_ERROR", "결제에 실패했어요. 같은 문제가 반복된다면 은행이나 카드사로 문의해주세요."),
    RESTRICTED_TRANSFER_ACCOUNT(
            "PAYMENTS_CONFIRM_RESTRICTED_TRANSFER_ACCOUNT",
            "계좌는 등록 후 12시간 뒤부터 결제할 수 있습니다. 관련 정책은 해당 은행으로 문의해주세요.");

    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().code(code).reason(reason).build();
    }
}
