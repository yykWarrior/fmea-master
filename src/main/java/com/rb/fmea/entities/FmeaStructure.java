package com.rb.fmea.entities;

import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
public class FmeaStructure implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_structure.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_structure.structure_name
     *
     * @mbggenerated
     */
    private String structureName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_structure.structure_desc
     *
     * @mbggenerated
     */
    private String structureDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_structure.superior_id
     *
     * @mbggenerated
     */
    private Integer superiorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_structure.fmea_id
     *
     * @mbggenerated
     */
    private Integer fmeaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_structure.id
     *
     * @return the value of fmea_structure.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_structure.id
     *
     * @param id the value for fmea_structure.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_structure.structure_name
     *
     * @return the value of fmea_structure.structure_name
     *
     * @mbggenerated
     */
    public String getStructureName() {
        return structureName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_structure.structure_name
     *
     * @param structureName the value for fmea_structure.structure_name
     *
     * @mbggenerated
     */
    public void setStructureName(String structureName) {
        this.structureName = structureName == null ? null : structureName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_structure.structure_desc
     *
     * @return the value of fmea_structure.structure_desc
     *
     * @mbggenerated
     */
    public String getStructureDesc() {
        return structureDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_structure.structure_desc
     *
     * @param structureDesc the value for fmea_structure.structure_desc
     *
     * @mbggenerated
     */
    public void setStructureDesc(String structureDesc) {
        this.structureDesc = structureDesc == null ? null : structureDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_structure.superior_id
     *
     * @return the value of fmea_structure.superior_id
     *
     * @mbggenerated
     */
    public Integer getSuperiorId() {
        return superiorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_structure.superior_id
     *
     * @param superiorId the value for fmea_structure.superior_id
     *
     * @mbggenerated
     */
    public void setSuperiorId(Integer superiorId) {
        this.superiorId = superiorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_structure.fmea_id
     *
     * @return the value of fmea_structure.fmea_id
     *
     * @mbggenerated
     */
    public Integer getFmeaId() {
        return fmeaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_structure.fmea_id
     *
     * @param fmeaId the value for fmea_structure.fmea_id
     *
     * @mbggenerated
     */
    public void setFmeaId(Integer fmeaId) {
        this.fmeaId = fmeaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", structureName=").append(structureName);
        sb.append(", structureDesc=").append(structureDesc);
        sb.append(", superiorId=").append(superiorId);
        sb.append(", fmeaId=").append(fmeaId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public FmeaStructure(String structureName, String structureDesc, Integer superiorId, Integer fmeaId) {
        this.structureName = structureName;
        this.structureDesc = structureDesc;
        this.superiorId = superiorId;
        this.fmeaId = fmeaId;
    }
}