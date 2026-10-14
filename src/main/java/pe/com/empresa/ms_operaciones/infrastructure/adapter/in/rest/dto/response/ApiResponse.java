package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

public record ApiResponse<T>(
        Integer status,
        String message,
        T data
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                ResponseStatus.SUCCESS.getCode(),
                ResponseStatus.SUCCESS.getMessage(),
                data
        );
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(
                ResponseStatus.CREATED.getCode(),
                ResponseStatus.CREATED.getMessage(),
                data
        );
    }

    public static <T> ApiResponse<T> error(ResponseStatus status) {
        return new ApiResponse<>(
                status.getCode(),
                status.getMessage(),
                null
        );
    }
}