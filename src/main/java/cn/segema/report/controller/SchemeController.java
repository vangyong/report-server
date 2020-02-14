package cn.segema.report.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.report.domain.Scheme;
import cn.segema.report.repository.SchemeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "方案")
@RestController
@RequestMapping(value = "/v2/scheme")
public class SchemeController {

	@Autowired
	private SchemeRepository schemeRepository;

	@ApiOperation(value = "根据id获取方案", notes = "根据id获取方案")
	@ApiImplicitParams({ @ApiImplicitParam(name = "schemeId", value = "方案id", required = true, paramType = "path") })
	@GetMapping("/{schemeId}")
	public ResponseEntity findById(@PathVariable String schemeId) {
		Optional<Scheme> scheme = this.schemeRepository.findById(schemeId);
		return new ResponseEntity(scheme.get(), HttpStatus.OK);
	}

	@ApiOperation(value = "获取方案列表", notes = "获取方案列表")
	@GetMapping("/list")
	public ResponseEntity findList(Scheme scheme) {
		List<Scheme> AddressList = schemeRepository.findListByScheme(scheme);
		return new ResponseEntity(AddressList, HttpStatus.OK);
	}

	@ApiOperation(value = "新增方案", notes = "新增方案")
	@PostMapping
	public ResponseEntity add(@RequestBody Scheme scheme) {
		scheme.setSchemeId(UUID.randomUUID().toString());
		schemeRepository.save(scheme);
		return new ResponseEntity(scheme, HttpStatus.OK);
	}

	@ApiOperation(value = "编辑方案", notes = "编辑方案")
	@PutMapping
	public ResponseEntity edit(@RequestBody Scheme scheme) {
		Optional<Scheme> oldScheme = schemeRepository.findById(scheme.getSchemeId());
		if (oldScheme.isPresent()) {
			BeanUtils.copyProperties(scheme, oldScheme.get(), "createTime");
			schemeRepository.save(oldScheme.get());
			return new ResponseEntity(oldScheme.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity("can't find chanel", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "删除方案", notes = "删除方案")
	@DeleteMapping("/{schemeId}")
	public ResponseEntity delete(@PathVariable String schemeId) {
		Optional<Scheme> scheme = schemeRepository.findById(schemeId);
		if (scheme.isPresent()) {
			schemeRepository.save(scheme.get());
		}
		return new ResponseEntity(scheme, HttpStatus.OK);
	}

}
