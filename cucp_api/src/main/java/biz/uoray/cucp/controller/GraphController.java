package biz.uoray.cucp.controller;

import biz.uoray.cucp.response.graph.ResponseChart;
import biz.uoray.cucp.service.GraphService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
@RequestMapping(path = "/v1/graph")
@Api(value = "グラフAPI", tags = "GraphApi", produces = "application/json")
public class GraphController {

    @Autowired
    GraphService graphService;

    /**
     * グラフ描画用Jsonを返す
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "グラフ描画用の車種詳細を取得する", nickname = "getChart")
    @GetMapping(produces = "application/json")
    public ResponseChart get() {
        return new ResponseChart(graphService.getCarDetailForGraph());
    }
}


