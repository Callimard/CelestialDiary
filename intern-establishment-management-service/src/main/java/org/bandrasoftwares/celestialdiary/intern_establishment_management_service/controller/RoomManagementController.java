package org.bandrasoftwares.celestialdiary.intern_establishment_management_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bandrasoftwares.celestialdiary.intern_establishment_management_service.service.RoomManagementService;
import org.bandrsoftwares.celestialdiary.model.dto.establishment.RoomDTO;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.room.CreateRoomPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.room.DeleteRoomPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.room.ReadRoomPrivilege;
import org.bandrsoftwares.celestialdiary.security.privilege.establishment.room.UpdateRoomPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bandrsoftwares.celestialdiary.api.v1.ApiInternEstablishmentV1.ROOMS_URL;
import static org.bandrsoftwares.celestialdiary.api.v1.ApiInternEstablishmentV1.SPECIFIC_ROOM;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ROOMS_URL)
public class RoomManagementController {

    // Variables.

    private final RoomManagementService roomManagementService;

    // Methods.

    @ReadRoomPrivilege
    @GetMapping
    List<RoomDTO> getRooms(@PathVariable(name = "idCompany") String idCompany,
                           @PathVariable(name = "idEstablishment") String idEstablishment,
                           @RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return roomManagementService.allRooms(idCompany, idEstablishment).stream().map(RoomDTO::new).toList();
        } else {
            return roomManagementService.searchRoom(idCompany, idEstablishment, filter).stream().map(RoomDTO::new).toList();
        }
    }

    @ReadRoomPrivilege
    @GetMapping(SPECIFIC_ROOM)
    public RoomDTO getSpecificRoom(@PathVariable(name = "idCompany") String idCompany,
                                   @PathVariable(name = "idEstablishment") String idEstablishment,
                                   @PathVariable(name = "roomName") String roomName) {
        return new RoomDTO(roomManagementService.getSpecificRoom(idCompany, idEstablishment, roomName));
    }

    @CreateRoomPrivilege
    @PostMapping()
    public RoomDTO createRoom(@PathVariable(name = "idCompany") String idCompany,
                              @PathVariable(name = "idEstablishment") String idEstablishment,
                              @RequestBody RoomManagementService.RoomCreationInformation information) {
        return new RoomDTO(roomManagementService.createRoom(idCompany, idEstablishment, information));
    }

    @UpdateRoomPrivilege
    @PutMapping(SPECIFIC_ROOM)
    public RoomDTO updateRoom(@PathVariable(name = "idCompany") String idCompany,
                              @PathVariable(name = "idEstablishment") String idEstablishment,
                              @PathVariable(name = "roomName") String roomName,
                              @RequestBody RoomManagementService.RoomUpdatedInformation information) {
        return new RoomDTO(roomManagementService.updateRoom(idCompany, idEstablishment, roomName, information));
    }

    @DeleteRoomPrivilege
    @DeleteMapping(SPECIFIC_ROOM)
    public void deleteRoom(@PathVariable(name = "idCompany") String idCompany,
                           @PathVariable(name = "idEstablishment") String idEstablishment,
                           @PathVariable(name = "roomName") String roomName) {
        roomManagementService.deleteRoom(idCompany, idEstablishment, roomName);
    }
}
