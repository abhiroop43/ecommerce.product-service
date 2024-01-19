package dev.abhiroopsantra.productservice.handlers;

import dev.abhiroopsantra.productservice.dto.ApiResponse;
import dev.abhiroopsantra.productservice.exceptions.BadRequestException;
import dev.abhiroopsantra.productservice.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice @Slf4j public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) public ResponseEntity<ApiResponse> handleException(Exception exception) {
        log.error("Internal Server Error: ", exception);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.errCode    = "500";
        apiResponse.errMessage = exception.getMessage();

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class) public ResponseEntity<ApiResponse> handleBadRequestException(
            BadRequestException exception
                                                                                                             ) {
        log.error("Bad Request: ", exception);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.errCode    = "400";
        apiResponse.errMessage = "The specified request is invalid";

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class) public ResponseEntity<ApiResponse> handleNotFoundException(
            NotFoundException exception
                                                                                                         ) {
        log.error("No Data Found: ", exception);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.errCode    = "404";
        apiResponse.errMessage = "The requested resource was not found";

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
