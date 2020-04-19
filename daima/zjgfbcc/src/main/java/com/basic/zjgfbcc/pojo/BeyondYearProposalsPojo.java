package com.basic.zjgfbcc.pojo;

/**
 * @author wzl
 */
public class BeyondYearProposalsPojo {

    /**
     * 已解决
     */
    private Integer resolved = 0;

    /**
     * 基本解决
     */
    private Integer basicSolution = 0;

    /**
     * 正在解决
     */
    private Integer areSolving = 0;

    /**
     * 不能解决
     */
    private Integer notSolve = 0;

    public Integer getResolved() {
        return resolved;
    }

    public void setResolved(Integer resolved) {
        this.resolved = resolved;
    }

    public Integer getBasicSolution() {
        return basicSolution;
    }

    public void setBasicSolution(Integer basicSolution) {
        this.basicSolution = basicSolution;
    }

    public Integer getAreSolving() {
        return areSolving;
    }

    public void setAreSolving(Integer areSolving) {
        this.areSolving = areSolving;
    }

    public Integer getNotSolve() {
        return notSolve;
    }

    public void setNotSolve(Integer notSolve) {
        this.notSolve = notSolve;
    }
}
