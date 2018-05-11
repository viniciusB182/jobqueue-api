(ns jobqueueapi.payloads.response)

(def validJobResult [
    {
      :job_assigned {
        :job_id "f26e890b-df8e-422e-a39c-7762aa0bac36"
        :agent_id "8ab86c18-3fae-4804-bfd9-c3d6e8f66260"
      }
}])

(def brokenJobResult [
  {
    :job_assigned {
      :job_id nil
      :agent_id "8ab86c18-3fae-4804-bfd9-c3d6e8f66260"
    }
}])

(def allBrokenResult [])

(def exerciseResult [
  {
    :job_assigned {
      :job_id "c0033410-981c-428a-954a-35dec05ef1d2",
      :agent_id "8ab86c18-3fae-4804-bfd9-c3d6e8f66260"
    }
  },
  {
    :job_assigned {
      :job_id "f26e890b-df8e-422e-a39c-7762aa0bac36",
      :agent_id "ed0e23ef-6c2b-430c-9b90-cd4f1ff74c88"
    }
  }
])