package com.didi.apiboss.service;

import com.didi.apiboss.remote.ServiceDriverUserClient;
import com.didi2023.internalcommon.dto.DriverCarBindingRelationship;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
        public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
            return serviceDriverUserClient.bind(driverCarBindingRelationship);
        }

        public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
            return serviceDriverUserClient.unbind(driverCarBindingRelationship);
        }
}
