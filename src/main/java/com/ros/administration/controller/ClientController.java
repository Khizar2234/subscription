package com.ros.administration.controller;

import com.ros.administration.controller.dto.RestaurantIntegrationDto;
import com.ros.administration.controller.dto.account.AccountDto;
import com.ros.administration.controller.dto.account.AccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAccountDto;
import com.ros.administration.controller.dto.account.ClientAccountSubscriptionDto;
import com.ros.administration.controller.dto.account.ClientAddDto;
import com.ros.administration.controller.dto.account.ClientDetailedInfoDto;
import com.ros.administration.controller.dto.account.ClientDto;
import com.ros.administration.exceptions.AccountSubscriptionNotFoundException;
import com.ros.administration.exceptions.ClientAccountStatusNotFoundException;
import com.ros.administration.exceptions.ClientNotFoundException;
import com.ros.administration.exceptions.IntegrationNotFoundException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.log.RosLogDebug;
import com.ros.administration.model.account.Client;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.service.ClientService;
import com.ros.administration.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin("*")
@Slf4j
public class ClientController {

	// Client Controller
	@Autowired
	private ClientService clientService;

	@PostMapping
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Add Client")
	public ResponseEntity<?> addClient(@RequestBody ClientAddDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Adding new Confiuration: {}", dto);
			response = new ResponseEntity<ClientDto>(clientService.addClient(dto), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Client Details based on id")
	public ResponseEntity<?> findClientById(@PathVariable(value = "id") UUID id) throws ClientNotFoundException {

		ResponseEntity<?> response;
		log.info("Getting Client Details by id : {}", id);
		response = new ResponseEntity<ClientDto>(clientService.getClientDetailsById(id), HttpStatus.OK);
		return response;
	}

	@GetMapping("/DetailedclientInformation/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Detailed Client Information based on id")
	public ResponseEntity<?> findClientDetailsById(@PathVariable(value = "id") UUID id) throws ClientNotFoundException {

		ResponseEntity<?> response;
		log.info("Getting Client Details by id : {}", id);
		response = new ResponseEntity<ClientDetailedInfoDto>(clientService.getClientInfoDetails(id), HttpStatus.OK);
		return response;
	}

	@Operation(summary = "get Client Account subcriptions")
    @RosLogDebug
    @GetMapping("/getClientAccountSubscriptions")
    public ResponseEntity<?> getAccountSubscriptionsForClient(@RequestParam UUID clientId){
    ResponseEntity<?> response;
    try {
    response = new ResponseEntity<ClientAccountSubscriptionDto>(clientService.getAccountSubscriptionsForClient(clientId), HttpStatus.OK);
    } catch (AccountSubscriptionNotFoundException e) {
        response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
    }
	
	@PutMapping
	@RosLogDebug
	@Operation(summary = "Editing the Client Details")
	public ResponseEntity<?> updateClient(@RequestBody ClientDto clientdto) throws ClientNotFoundException {

		ResponseEntity<?> response;
		log.info("Updating Confiuration: {}", clientdto);
		response = new ResponseEntity<ClientDto>(clientService.updateClient(clientdto), HttpStatus.OK);

		return response;
	}

	@Operation(summary = "get client frequency")
	@GetMapping("/getAllClients")
	public ResponseEntity<?> getAllClients() {
		ResponseEntity<?> response;
		response = new ResponseEntity<List<ClientDetailedInfoDto>>(clientService.viewAllClients(), HttpStatus.OK);
		return response;
	}

	@GetMapping("/search/{clientName}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Search Clients based on name")
	public ResponseEntity<?> findClientByName(@PathVariable(value = "clientName") String clientName)
			throws ClientNotFoundException {

		ResponseEntity<?> response;
		log.info("Getting Client Details by name : {}", clientName);
		response = new ResponseEntity<Client>(clientService.getClient(clientName), HttpStatus.OK);
		return response;
	}

	@Operation(summary = "delete client")
	@DeleteMapping
	public ResponseEntity<?> deleteClient(@RequestParam UUID id) {

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<String>(clientService.deleteClient(id), HttpStatus.OK);

		} catch (ClientNotFoundException e) {

			response = new ResponseEntity<ClientNotFoundException>(new ClientNotFoundException(e.getMessage()),
					HttpStatus.BAD_REQUEST);

		}

		return response;
	}
	
	@Operation(summary = "edit client Estatus")
	@PutMapping("/editClientEstatus")
	public ResponseEntity<?> editClientEstatus(@RequestParam UUID id, @RequestParam EStatus accountStatus) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<ClientDetailedInfoDto>(
					clientService.editClientEstatus(id, accountStatus), HttpStatus.OK);

		} catch (ClientAccountStatusNotFoundException e) {
			// TODO Auto-generated catch block
			
			response = new ResponseEntity<ClientAccountStatusNotFoundException>(
					new ClientAccountStatusNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "get client by primaryContactEmail")
	@GetMapping("/getByEmail")
	@ResponseBody
	public ResponseEntity<?> getClientByEmail(@RequestParam String primaryContactEmail) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<ClientDetailedInfoDto>(clientService.viewClientByEmail(primaryContactEmail), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Object>( e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	@Operation(summary = "get client by accountId")
	@GetMapping("/getByaccountId")
	@ResponseBody
	public ResponseEntity<?> getClientByEmail(@RequestParam UUID id) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<ClientDetailedInfoDto>(clientService.getClientDetailsByAccountId(id), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<Object>( e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}

	
}
